package com.notebook.notebook.controller

import com.notebook.notebook.common.MessageType
import java.util.*

data class MessageDTO(

        val id: Long,

        val createdAt: Date,

        val updatedAt: Date,

        val dateSent: Date,

        val type: MessageType,

        val sender: String,

        val receiver: String,

        val content: String,
)
