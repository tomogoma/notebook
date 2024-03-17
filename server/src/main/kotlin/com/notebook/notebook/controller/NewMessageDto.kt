package com.notebook.notebook.controller

import com.notebook.notebook.common.MessageType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

// TODO: improve validation error messages

data class NewMessageDto(

        @NotNull
        val dateSent: Date,

        @NotNull
        val type: MessageType,

        @NotEmpty
        val sender: String,

        @NotEmpty
        val receiver: String,

        @NotEmpty
        val content: String
)
