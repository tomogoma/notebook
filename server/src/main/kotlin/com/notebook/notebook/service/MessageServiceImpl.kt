package com.notebook.notebook.service

import com.notebook.notebook.entity.Message
import com.notebook.notebook.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val messageRepository: MessageRepository) : MessageService {
    override fun fetchMessages(): List<Message> {
        return messageRepository.findAll().toList()
    }

    override fun saveMessage(message: Message): Message {
        return messageRepository.save(message)
    }

    override fun saveMessages(messages: List<Message>): List<Message> {
        return messageRepository.saveAll(messages).toList()
    }
}
