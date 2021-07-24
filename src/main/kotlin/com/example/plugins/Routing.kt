package com.example.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hi", contentType = ContentType.Text.Plain)
        }

        route("/notes") {

            post("/create") {
                val body = call.receive<String>()
                call.respond(body)
            }

        }
    }
}
