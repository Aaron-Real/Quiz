package com.aaron.data.repository

import com.aaron.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.aaron.domain.model.QuizQuestion
import com.aaron.domain.repository.QuizQuestionRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
): QuizQuestionRepository {

    val quizQuestionCollection = mongoDatabase
        .getCollection<QuizQuestion>(QUESTIONS_COLLECTION_NAME)

    val quizQuestions = mutableListOf<QuizQuestion>()

    override suspend fun getAllQuizQuestions(
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

    override suspend fun getQuizQuestionById(id: String?): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override suspend fun upsertQuizQuestion(question: QuizQuestion) {
        quizQuestionCollection.insertOne(question)

    }

    override suspend fun deleteQuizQuestionsById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }

    }

}