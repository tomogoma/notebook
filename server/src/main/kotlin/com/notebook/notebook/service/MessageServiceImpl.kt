package com.notebook.notebook.service

import com.notebook.notebook.entity.Message
import com.notebook.notebook.repository.MessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageServiceImpl(private val messageRepository: MessageRepository) : MessageService {
    override fun fetchMessages(delta: Date?, pageable: Pageable): Page<Message> {
        if (delta == null) {
            return messageRepository.findAll(pageable)
        }
        return messageRepository.findByCreatedAtAfterOrUpdatedAtAfterOrDeletedAtAfter(delta, delta, delta, pageable)
    }

    override fun saveMessage(message: Message): Message {
        return messageRepository.save(message)
    }

    override fun saveMessages(messages: List<Message>): List<Message> {
        return messageRepository.saveAll(messages).toList()
    }
}
