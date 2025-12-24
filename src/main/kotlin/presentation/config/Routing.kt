package com.aaron.presentation.config

import com.aaron.data.database.DatabaseFactory
import com.aaron.data.repository.QuizQuestionRepositoryImpl
import com.aaron.domain.repository.QuizQuestionRepository
import com.aaron.presentation.routes.quiz_questions.deleteQuizQuestionsById
import com.aaron.presentation.routes.quiz_questions.getAllQuizQuestions
import com.aaron.presentation.routes.quiz_questions.getQuizQuestionById
import com.aaron.presentation.routes.quiz_questions.upsertQuizQuestion
import com.aaron.presentation.routes.root
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting(){

    val mongoDatabase = DatabaseFactory.create()

    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)

    routing {

        root()

        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionsById(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
    }

}

