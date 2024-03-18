package com.notebook.notebook.converter

import com.notebook.notebook.controller.MessageDto
import com.notebook.notebook.controller.NewMessageDto
import com.notebook.notebook.entity.Message
import org.springframework.stereotype.Service

// TODO: use MapStruct instead of manual implementation below. Abandoned because the bean is not being found as expected
// @Mapper(componentModel = "spring")
// TODO if using MapStruct fails then split this into an interface and implementation

@Service
class MessageConverter {
    fun newMessageDtoToMessage(nmDTO: NewMessageDto): Message {
        return Message(
            nmDTO.dateSent,
            nmDTO.type,
            nmDTO.sender,
            nmDTO.receiver,
            nmDTO.content
        )
    }

    fun messageToMessageDto(msg: Message): MessageDto {
        return MessageDto(
            msg.id!!,
            msg.createdAt,
            msg.updatedAt,
            msg.dateSent,
            msg.type,
            msg.sender,
            msg.receiver,
            msg.content
        )
    }
}