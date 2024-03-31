package com.notebook.notebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotebookApplication

// TODO: add default 404 error page
// TODO: add standard error return
// TODO: add flyway db migrations:
//     https://blog.tericcabrel.com/handle-database-migrations-in-a-springboot-application-with-flyway/

fun main(args: Array<String>) {
    runApplication<NotebookApplication>(*args)
}
