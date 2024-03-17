package com.notebook.notebook.entity

import com.notebook.notebook.common.MessageType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@Data
data class Message(

        val dateSent: Date = Date(),

        val type: MessageType = MessageType.SMS,

        val sender: String = "",

        val receiver: String = "",

        val content: String = "",

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        val createdAt: Date = Date(),

        val updatedAt: Date = Date()
)
