package com.aaron.presentation.routes.quiz_questions

import com.aaron.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete

fun Route.deleteQuizQuestionsById(
    repository: QuizQuestionRepository
) {

    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                message = "Question ID is required",
                status = HttpStatusCode.BadRequest
            )
            return@delete
        }

        val isDeleted  = repository.deleteQuizQuestionsById(id)
        if (isDeleted) {
            call.respond(
                message = "Question Deleted successfully!",
                status = HttpStatusCode.NoContent
            )
        } else {
            call.respond(
                message = "No Question to Delete",
                status = HttpStatusCode.NotFound
            )
        }

    }
}