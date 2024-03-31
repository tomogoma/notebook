package com.notebook.notebook.exception

import com.notebook.notebook.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun notValid(e: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val messages = e.allErrors.map { it.defaultMessage!! }
        return ResponseEntity(ErrorDto(messages), HttpStatus.BAD_REQUEST)
    }

}