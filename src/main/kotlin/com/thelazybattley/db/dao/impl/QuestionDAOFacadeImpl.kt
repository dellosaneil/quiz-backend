package com.thelazybattley.db.dao.impl

import com.thelazybattley.db.dao.QuestionDAOFacade
import com.thelazybattley.db.dbQuery
import com.thelazybattley.db.entity.QuestionEntity
import com.thelazybattley.db.entity.QuestionsEntity
import com.thelazybattley.mapper.toQuestion
import com.thelazybattley.models.Question
import org.jetbrains.exposed.sql.*

class QuestionDAOFacadeImpl : QuestionDAOFacade {

    private fun resultRowToQuestion(row: ResultRow) = QuestionEntity(
        question = row[QuestionsEntity.question],
        answer = row[QuestionsEntity.answer],
        choices = row[QuestionsEntity.choices]
    )

    override suspend fun insertQuestion(newQuestion: QuestionEntity): Question? = dbQuery {
        val id = QuestionsEntity.insertAndGetId {
            it[question] = newQuestion.question
            it[answer] = newQuestion.answer
            it[choices] = newQuestion.choices
        }
        getQuestion(id = id.value)
    }

    override suspend fun getQuestion(id: Int) = run {
        QuestionsEntity
            .select { QuestionsEntity.id eq id }
            .map(::resultRowToQuestion)
            .singleOrNull()
            ?.toQuestion
    }

    override suspend fun allQuestions() = dbQuery {
        QuestionsEntity.selectAll().map(::resultRowToQuestion)
    }

    override suspend fun clearQuestions() {
        QuestionsEntity.deleteAll()
    }

    override suspend fun updateQuestion(id: Int, updatedQuestion: QuestionEntity) {
        QuestionsEntity.update({ QuestionsEntity.id eq id }) {
            it[question] = updatedQuestion.question
            it[answer] = updatedQuestion.answer
            it[choices] = updatedQuestion.choices
        }
    }
}