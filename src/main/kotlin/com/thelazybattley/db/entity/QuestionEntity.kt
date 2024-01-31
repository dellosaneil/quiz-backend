package com.thelazybattley.db.entity

import org.jetbrains.exposed.dao.id.IntIdTable


data class QuestionEntity(
    val question: String,
    val answer: String,
    val choices: String
)

object QuestionsEntity : IntIdTable() {
    val question = varchar("question", 255)
    val answer = varchar("answer", 255)
    val choices = text("choices")
}