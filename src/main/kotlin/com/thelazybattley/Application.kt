package com.thelazybattley

import com.thelazybattley.db.DatabaseSingleton
import com.thelazybattley.plugins.configureMonitoring
import com.thelazybattley.plugins.configureRouting
import com.thelazybattley.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    DatabaseSingleton.init()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
