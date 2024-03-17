package com.notebook.notebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotebookApplication

// TODO: add default 404 error page
// TODO: add standard error return

fun main(args: Array<String>) {
    runApplication<NotebookApplication>(*args)
}
