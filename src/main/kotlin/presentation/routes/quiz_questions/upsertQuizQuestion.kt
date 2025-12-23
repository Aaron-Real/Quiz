package com.aaron.presentation.routes.quiz_questions

import com.aaron.domain.model.QuizQuestion
import com.aaron.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {

    post("/quiz/questions") {

        val question = call.receive<QuizQuestion>()
        repository.upsertQuizQuestion(question)
        call.respond(
            status = HttpStatusCode.Created,
            message = "Question added successfully!"
        )

    }

}