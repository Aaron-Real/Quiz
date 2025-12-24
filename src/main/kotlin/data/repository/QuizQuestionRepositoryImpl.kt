package com.aaron.data.repository

import com.aaron.data.database.entity.QuizQuestionEntity
import com.aaron.data.database.mapper.toQuizQuestion
import com.aaron.data.database.mapper.toQuizQuestionEntity
import com.aaron.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.aaron.domain.model.QuizQuestion
import com.aaron.domain.repository.QuizQuestionRepository
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizQuestionRepository {

    val quizQuestionCollection = mongoDatabase
        .getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)


    override suspend fun getAllQuizQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {

        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::topicCode.name, it)
            } ?: Filters.empty()

            quizQuestionCollection
                .find(filter = filterQuery)
                .limit(questionLimit)
                .map { it.toQuizQuestion() }
                .toList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

    }

    override suspend fun getQuizQuestionById(id: String?): QuizQuestion? {

        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name,
                id
            )
            val questionEntity = quizQuestionCollection
                .find(filter = filterQuery)
                .firstOrNull()
            questionEntity?.toQuizQuestion()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun upsertQuizQuestion(question: QuizQuestion) {

        try {
            if (question.id == null) {
                quizQuestionCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(
                    QuizQuestionEntity::_id.name, question.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, question.question),
                    Updates.set(QuizQuestionEntity::correctOption.name, question.correctOption),
                    Updates.set(QuizQuestionEntity::incorrectOptions.name, question.incorrectOptions),
                    Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode),
                )
                quizQuestionCollection.updateOne(filterQuery, updateQuery)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun deleteQuizQuestionsById(id: String): Boolean {

        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val deleteResult = quizQuestionCollection
                .deleteOne(filter = filterQuery)
            deleteResult.deletedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}