package com.thelazybattley.payloads

import kotlinx.serialization.Serializable

@Serializable
data class QuestionPayload(
    val question: String,
    val answer: String,
    val choices: List<String>
)
