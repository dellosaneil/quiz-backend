package com.thelazybattley.plugins

import com.thelazybattley.routes.questionsRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        questionsRouting()
    }
}
