package com.aaron.presentation.routes.quiz_questions

import com.aaron.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepository
) {

    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                message = "Question ID is required",
                status = HttpStatusCode.BadRequest
            )
            return@get
        }

        val question = repository.getQuizQuestionById(id)

        if (question != null) {
            call.respond(
                message = question,
                status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "No question found with id $id",
                status = HttpStatusCode.NotFound
            )
        }
    }
}