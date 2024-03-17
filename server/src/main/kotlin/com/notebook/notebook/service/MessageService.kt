package com.notebook.notebook.service

import com.notebook.notebook.entity.Message


interface MessageService {
    fun saveMessage(message: Message): Message

    fun saveMessages(messages: List<Message>): List<Message>

    fun fetchMessages(): List<Message>
}
