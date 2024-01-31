package com.thelazybattley.models

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val question: String,
    val answer: String,
    val choices: List<String>,
    val type: String
)
