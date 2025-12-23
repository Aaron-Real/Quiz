package com.aaron.data.repository

import com.aaron.domain.model.QuizQuestion
import com.aaron.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl: QuizQuestionRepository {

    val quizQuestions = mutableListOf<QuizQuestion>()

    override fun getAllQuizQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return if (topicCode != null) {
            quizQuestions
                .filter { topicCode == it.topicCode }
                .take(limit ?: quizQuestions.size)
        } else {
            quizQuestions
                .take(limit ?: quizQuestions.size)
        }
    }

    override fun getQuizQuestionById(id: String?): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override fun upsertQuizQuestion(question: QuizQuestion) {
        quizQuestions.add(question)

    }

    override fun deleteQuizQuestionsById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }

    }

}