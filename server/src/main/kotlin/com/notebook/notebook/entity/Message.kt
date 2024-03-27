package com.notebook.notebook.entity

import com.notebook.notebook.common.MessageType
import jakarta.persistence.*
import java.util.*

@Entity(name = "messages")
data class Message(

    @Column(name = "date_sent", nullable = false)
    val dateSent: Date = Date(),

    @Column(name = "type", nullable = false)
    val type: MessageType = MessageType.SMS,

    @Column(name = "sender", nullable = false)
    val sender: String = "",

    @Column(name = "receiver", nullable = false)
    val receiver: String = "",

    @Column(name = "content", nullable = false)
    val content: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Date = Date(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: Date = Date(),

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: Date? = null
)
