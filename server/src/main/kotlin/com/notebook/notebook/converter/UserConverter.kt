package com.notebook.notebook.converter

import com.notebook.notebook.dto.UserDto
import com.notebook.notebook.entity.User
import org.springframework.stereotype.Service

@Service
class UserConverter {

    fun userToUserDto(user: User): UserDto {
        return UserDto(
            user.username,
            user.createdAt,
            user.updatedAt,
            user.deletedAt,
            user.lockedAt,
            user.disabledAt,
            user.passwordUpdatedAt
        )
    }
}