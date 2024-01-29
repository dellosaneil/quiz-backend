package com.thelazybattley.db.dao.impl

import com.thelazybattley.db.dao.QuestionDAOFacade
import com.thelazybattley.db.dbQuery
import com.thelazybattley.models.Question
import com.thelazybattley.models.Questions
import org.jetbrains.exposed.sql.*

class QuestionDAOFacadeImpl : QuestionDAOFacade {
    private fun resultRowToQuestion(row: ResultRow) = Question(
        id = row[Questions.id],
        question = row[Questions.question],
        answer = row[Questions.answer]
    )


    override suspend fun insertQuestion(newQuestion: Question): Question? = dbQuery {
        val q = Questions.insert {
            it[question] = newQuestion.question
            it[answer] = newQuestion.answer
        }
        q.resultedValues?.singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun getQuestion(id: Int) = dbQuery {
        Questions
            .select { Questions.id eq id }
            .map(::resultRowToQuestion)
            .singleOrNull()
    }

    override suspend fun allQuestions() = dbQuery {
        Questions.selectAll().map(::resultRowToQuestion)
    }

    override suspend fun clearQuestions() {
        Questions.deleteAll()
    }
}