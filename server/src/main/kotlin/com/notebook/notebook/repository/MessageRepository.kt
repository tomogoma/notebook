package com.notebook.notebook.repository

import com.notebook.notebook.entity.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Message, Long> {

    fun findByCreatedAtAfterOrUpdatedAtAfterOrDeletedAtAfter(
        createdAfter: Date,
        updatedAfter: Date,
        deletedAfter: Date,
        page: Pageable? = null
    ): Page<Message>
}
