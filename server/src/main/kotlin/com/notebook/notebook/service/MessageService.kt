package com.notebook.notebook.service

import com.notebook.notebook.entity.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*


interface MessageService {
    fun saveMessage(message: Message): Message

    fun saveMessages(messages: List<Message>): List<Message>

    fun fetchMessages(delta: Date?, pageable: Pageable): Page<Message>
}
