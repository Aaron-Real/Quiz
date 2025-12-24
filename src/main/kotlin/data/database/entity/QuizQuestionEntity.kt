package com.aaron.data.database.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class QuizQuestionEntity(
    @BsonId
    val _id: String? = ObjectId().toString(),
    val question: String,
    val correctOption: String,
    val incorrectOptions: List<String>,
    val explanation: String,
    val topicCode: Int
)
