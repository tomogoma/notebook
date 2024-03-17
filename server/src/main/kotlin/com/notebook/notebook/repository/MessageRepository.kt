package com.notebook.notebook.repository

import com.notebook.notebook.entity.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long>
