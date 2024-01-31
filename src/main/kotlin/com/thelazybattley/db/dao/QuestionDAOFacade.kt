package com.thelazybattley.db.dao

import com.thelazybattley.db.entity.QuestionEntity
import com.thelazybattley.models.Question

interface QuestionDAOFacade {
    suspend fun insertQuestion(newQuestion: QuestionEntity) : Question?
    suspend fun getQuestion(id: Int) : Question?
    suspend fun allQuestions(): List<Question>
    suspend fun clearQuestions()
    suspend fun updateQuestion(id: Int, updatedQuestion: QuestionEntity)
}