package com.notebook.notebook.dto

data class LoginResponseDto(
    val token: String,
    val expiry: Long
)