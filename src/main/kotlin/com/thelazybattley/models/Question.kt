package com.thelazybattley.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Question(
    val id: Int = 0,
    val question: String,
    val answer: String
)

object Questions : Table() {
    val id = integer("id").autoIncrement()
    val question = varchar("question", 128)
    val answer = varchar("answer", 128)
    override val primaryKey = PrimaryKey(id)
}
