package com.notebook.notebook.controller

import com.notebook.notebook.converter.MessageConverter
import com.notebook.notebook.service.MessageService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class MessageController(
    private val messageService: MessageService,
    private val messageConverter: MessageConverter
) {

    @PostMapping("/messages")
    fun saveMessage(
        @Valid @RequestBody newMsgDto: NewMessageDto
    ): MessageDto {
        val unsavedMsg = messageConverter.newMessageDtoToMessage(newMsgDto)
        val savedMsg = messageService.saveMessage(unsavedMsg)
        return messageConverter.messageToMessageDto(savedMsg)
    }

    @PostMapping("/messages/bulk")
    fun saveMessages(
        @Valid @RequestBody newMsgDtos: List<NewMessageDto>
    ): List<MessageDto> {
        val unsavedMsgs = newMsgDtos.map { messageConverter.newMessageDtoToMessage(it) }
        val savedMsgs = messageService.saveMessages(unsavedMsgs)
        return savedMsgs.map { messageConverter.messageToMessageDto(it) }
    }

    @GetMapping("/messages")
    fun getMessages(
        @RequestParam("delta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) delta: Date?,
        page: Pageable,
        assembler: PagedResourcesAssembler<MessageDto>
    ): PagedModel<EntityModel<MessageDto>> {
        val msgs = messageService.fetchMessages(delta, page)
        val msgDtos = msgs.map { messageConverter.messageToMessageDto(it) }
        return assembler.toModel(msgDtos)
    }
}
