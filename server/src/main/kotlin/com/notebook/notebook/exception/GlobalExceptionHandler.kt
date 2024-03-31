package com.notebook.notebook.exception

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AccountStatusException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleSecurityException(exception: Exception): ProblemDetail {
        var problemDetail: ProblemDetail? = null

        // TODO send this stack trace to an observability tool
        exception.printStackTrace()

        if (exception is BadCredentialsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.message!!)
            problemDetail.setProperty("description", "The username or password is incorrect")
            return problemDetail
        }

        if (exception is AccountStatusException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message!!)
            problemDetail.setProperty("description", "The account is locked")
        }

        if (exception is AccessDeniedException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message!!)
            problemDetail.setProperty("description", "You are not authorized to access this resource")
        }

        if (exception is SignatureException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message!!)
            problemDetail.setProperty("description", "The JWT signature is invalid")
        }

        if (exception is ExpiredJwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message!!)
            problemDetail.setProperty("description", "The JWT token has expired")
        }

        if (problemDetail == null) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(500),
                "something wicked happened, please try again later"
            )
            problemDetail.setProperty("description", "Unknown internal server error.")
            problemDetail.setProperty("description", "Unknown internal server error.")
        }

        return problemDetail
    }
}