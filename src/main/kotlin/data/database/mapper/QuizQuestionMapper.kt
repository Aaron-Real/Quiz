package com.aaron.data.database.mapper

import com.aaron.data.database.entity.QuizQuestionEntity
import com.aaron.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id = _id,
    question = question,
    correctOption = correctOption,
    incorrectOptions = incorrectOptions,
    explanation = explanation,
    topicCode = topicCode
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity(
    question = question,
    correctOption = correctOption,
    incorrectOptions = incorrectOptions,
    explanation = explanation,
    topicCode = topicCode
)