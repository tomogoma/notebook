package com.notebook.notebook.dto

import java.util.*

data class UserDto(
    val username: String,
    val createdAt: Date,
    val updatedAt: Date?,
    val deletedAt: Date?,
    val lockedAt: Date?,
    val disabledAt: Date?,
    val passwordUpdatedAt: Date?
)
