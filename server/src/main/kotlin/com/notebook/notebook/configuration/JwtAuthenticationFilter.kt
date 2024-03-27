package com.notebook.notebook.configuration

import com.notebook.notebook.service.JWTService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import java.io.IOException

@Component
class JwtAuthenticationFilter(
    private val jwtService: JWTService,
    private val userDetailsService: UserDetailsService,
    private val handlerExceptionResolver: HandlerExceptionResolver
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {

        if (SecurityContextHolder.getContext().authentication != null) {
            filterChain.doFilter(request, response)
        }

        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val jwt = authHeader.substring(7).trim()

            if (!jwtService.isTokenValid(jwt)) {
                filterChain.doFilter(request, response)
                return
            }

            val username: String? = jwtService.extractUsername(jwt)
            if (username == null) {
                filterChain.doFilter(request, response)
                return
            }

            val userDetails = userDetailsService.loadUserByUsername(username)
            if (userDetails == null) {
                filterChain.doFilter(request, response)
                return
            }

            val authToken = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )

            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authToken
            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            // TODO Implement proper exception handling consider:
            //     SQL exceptions
            //     invalid jwt returns 200 as is
            handlerExceptionResolver.resolveException(request, response, null, exception)
        }
    }
}