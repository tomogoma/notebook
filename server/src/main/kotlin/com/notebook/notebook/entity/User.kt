package com.notebook.notebook.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity(name = "users")
class User(

    usernameArg: String = "",

    passwordArg: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Date = Date(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: Date = Date(),

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: Date? = null,

    @Column(name = "locked_at", nullable = true)
    val lockedAt: Date? = null,

    @Column(name = "disabled_at", nullable = true)
    val disabledAt: Date? = null,

    @Column(name = "password_updated_at", nullable = true)
    val passwordUpdatedAt: Date? = null

) : UserDetails {


    @Column(name = "username", nullable = false, unique = true)
    private val username = usernameArg

    @Column(name = "password", nullable = false)
    private val password = passwordArg
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // TODO add RBAC
        return mutableListOf()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return deletedAt == null
    }

    override fun isAccountNonLocked(): Boolean {
        return lockedAt == null && deletedAt == null
    }

    override fun isCredentialsNonExpired(): Boolean {
        // TODO: Number of days before password expiration should be configurable
        val passwordExpiryTimeMs = 1471228928 // 1 year
        if (createdAt.time + passwordExpiryTimeMs > Date().time) {
            return true
        }
        if (passwordUpdatedAt == null) {
            return false
        }
        return passwordUpdatedAt.time + passwordExpiryTimeMs > Date().time
    }

    override fun isEnabled(): Boolean {
        return disabledAt == null && deletedAt == null
    }
}
