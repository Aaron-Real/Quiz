package com.aaron.presentation.routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun Route.root(){
        get(path = "/"){
            call.respondText { "Welcome to Ktor API" }
        }
}