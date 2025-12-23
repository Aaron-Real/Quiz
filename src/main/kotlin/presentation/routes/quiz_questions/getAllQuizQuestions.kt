package com.aaron.presentation.routes.quiz_questions

import com.aaron.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions") {

        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val questions = repository.getAllQuizQuestions(topicCode, limit)


        if (questions.isNotEmpty()) {
            call.respond(
                message = questions,
                status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "Question not found",
                status = HttpStatusCode.NotFound
            )
        }

    }


}