package com.aaron.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestion(
    val id: String? = null,
    val question: String,
    val correctOption: String,
    val incorrectOptions: List<String>,
    val explanation: String,
    val topicCode: Int
)
