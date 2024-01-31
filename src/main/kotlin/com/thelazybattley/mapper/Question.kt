package com.thelazybattley.mapper

import com.thelazybattley.db.entity.QuestionEntity
import com.thelazybattley.models.Question
import com.thelazybattley.payloads.QuestionPayload
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val QuestionPayload.toEntity
    get() = run {
        QuestionEntity(
            question = question,
            answer = answer,
            choices = Json.encodeToString(choices),
            type = type
        )
    }

val QuestionEntity.toQuestion
    get() = run {
        Question(
            question = question,
            answer = answer,
            choices = Json.decodeFromString(choices),
            type = type
        )
    }