package com.notebook.notebook.controller

import com.notebook.notebook.converter.UserConverter
import com.notebook.notebook.dto.LoginResponseDto
import com.notebook.notebook.dto.LoginUserDto
import com.notebook.notebook.dto.RegisterUserDto
import com.notebook.notebook.dto.UserDto
import com.notebook.notebook.service.AuthenticationService
import com.notebook.notebook.service.JWTService
import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthenticationController(
    @Autowired val jwtService: JWTService,
    @Autowired val authenticationService: AuthenticationService,
    @Autowired val userConverter: UserConverter
) {

    @PostMapping("/signup")
    fun signup(@RequestBody registerUserDto: RegisterUserDto): ResponseEntity<UserDto> {
        val registeredUser = authenticationService.signup(registerUserDto)
        return ResponseEntity.ok(userConverter.userToUserDto(registeredUser))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginUserDto: LoginUserDto
    ): ResponseEntity<LoginResponseDto> {
        val authedUser = authenticationService.authenticate(loginUserDto)
        val jwt = jwtService.generateToken(authedUser!!)
        return ResponseEntity.ok(LoginResponseDto(jwt, jwtService.extractClaim(jwt, Claims::getExpiration)!!.time))
    }
}