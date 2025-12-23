package com.aaron.domain.repository

import com.aaron.domain.model.QuizQuestion

interface QuizQuestionRepository {

    fun getAllQuizQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    fun getQuizQuestionById(id: String?): QuizQuestion?
    fun upsertQuizQuestion(question: QuizQuestion)
    fun deleteQuizQuestionsById(id : String): Boolean
}