package com.notebook.notebook.controller

data class ErrorDto(
    val messages: List<String>,
    val from: ErrorDto? = null
)
