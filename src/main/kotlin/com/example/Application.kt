package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.repository.initDatabase

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        initDatabase()
        configureRouting()
//        configureSecurity()
        configureSerialization()
    }.start(wait = true)
}
