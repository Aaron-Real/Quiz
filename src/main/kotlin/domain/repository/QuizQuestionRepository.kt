package com.aaron.domain.repository

import com.aaron.domain.model.QuizQuestion

interface QuizQuestionRepository {

    suspend fun getAllQuizQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    suspend fun getQuizQuestionById(id: String?): QuizQuestion?
    suspend fun upsertQuizQuestion(question: QuizQuestion)
    suspend fun deleteQuizQuestionsById(id : String): Boolean
}