package com.aaron

import com.aaron.presentation.config.configureLogging
import com.aaron.presentation.config.configureRouting
import com.aaron.presentation.config.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {

    configureLogging()
    configureSerialization()
    configureRouting()

}
