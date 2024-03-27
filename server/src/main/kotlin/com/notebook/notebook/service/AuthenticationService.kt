package com.notebook.notebook.service

import com.notebook.notebook.dto.LoginUserDto
import com.notebook.notebook.dto.RegisterUserDto
import com.notebook.notebook.entity.User
import com.notebook.notebook.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    @Autowired val userRepository: UserRepository,
    @Autowired val passwordEncoder: PasswordEncoder,
    @Autowired val authenticationManager: AuthenticationManager
) {

    fun signup(request: RegisterUserDto): User {
        return userRepository.save(
            User(
                request.username,
                passwordEncoder.encode(request.password)
            )
        )
    }

    fun authenticate(request: LoginUserDto): User? {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        return userRepository.findByUsername(request.username)
    }
}