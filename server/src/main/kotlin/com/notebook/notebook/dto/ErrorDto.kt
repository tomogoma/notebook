package com.notebook.notebook.dto

data class ErrorDto(
    val messages: List<String>,
    val from: ErrorDto? = null
)
