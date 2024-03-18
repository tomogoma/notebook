package com.notebook.notebook.controller

import com.notebook.notebook.converter.MessageConverter
import com.notebook.notebook.service.MessageService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


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
    fun getMessages(): List<MessageDto> {
        val msgs = messageService.fetchMessages()
        return msgs.map { messageConverter.messageToMessageDto(it) }
    }
}
