package com.thelazybattley.db.entity

import org.jetbrains.exposed.dao.id.IntIdTable


data class QuestionEntity(
    val question: String,
    val answer: String,
    val choices: String,
    val type: String
)

object QuestionsEntity : IntIdTable() {
    val question = text("question")
    val answer = text("answer")
    val choices = text("choices")
    val type = text("type")
}