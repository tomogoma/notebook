package com.notebook.notebook.dto

import com.notebook.notebook.common.MessageType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

// TODO: improve validation error messages https://www.bezkoder.com/spring-boot-validate-request-body/

data class NewMessageDto(

    @NotNull(message = "dateSent is required")
    val dateSent: Date,

    @NotNull(message = "type is required")
    val type: MessageType,

    @NotEmpty(message = "sender cannot be empty")
    val sender: String,

    @NotEmpty(message = "receiver cannot be empty")
    val receiver: String,

    @NotEmpty(message = "content cannot be empty")
    val content: String
)
