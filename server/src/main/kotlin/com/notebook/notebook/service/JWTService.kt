package com.notebook.notebook.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class JWTService(
    @Value("\${security.jwt.token.secret-key}")
    private val secretKeyStr: String,

    @Value("\${security.jwt.token.expiration-time}")
    private val jwtExpiration: Long
) {

    private val secretKey = Keys.hmacShaKeyFor(secretKeyStr.toByteArray(StandardCharsets.UTF_8))

    fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T? {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(hashMapOf(), userDetails)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, jwtExpiration)
    }

    fun buildToken(extraClaims: Map<String, Any>, userDetails: UserDetails, expirationTime: Long): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(secretKey)
            .compact()
    }

    fun isTokenValid(token: String): Boolean {
        return !(isTokenExpired(token) ?: true)
    }

    private fun isTokenExpired(token: String): Boolean? {
        return extractExpiration(token)?.before(Date())
    }

    private fun extractExpiration(token: String): Date? {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }
}