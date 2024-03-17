package com.notebook.notebook.controller

import com.notebook.notebook.converter.MessageConverter
import com.notebook.notebook.service.MessageService
import org.springframework.validation.annotation.Validated
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
            @Validated @RequestBody newMsgDto: NewMessageDto
    ): MessageDTO {
        val unsavedMsg = messageConverter.newMessageDtoToMessage(newMsgDto)
        val savedMsg = messageService.saveMessage(unsavedMsg)
        return messageConverter.messageToMessageDTO(savedMsg)
    }

    @PostMapping("/messages/bulk")
    fun saveMessages(
            @Validated @RequestBody newMsgDtos: List<NewMessageDto>
    ): List<MessageDTO> {
        val unsavedMsgs = newMsgDtos.map { messageConverter.newMessageDtoToMessage(it) }
        val savedMsgs = messageService.saveMessages(unsavedMsgs)
        return savedMsgs.map { messageConverter.messageToMessageDTO(it) }
    }

    @GetMapping("/messages")
    fun getMessages(): List<MessageDTO> {
        val msgs = messageService.fetchMessages()
        return msgs.map { messageConverter.messageToMessageDTO(it) }
    }
}
