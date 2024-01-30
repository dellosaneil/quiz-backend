package com.thelazybattley.db.dao

import com.thelazybattley.models.Question

interface QuestionDAOFacade {
    suspend fun insertQuestion(newQuestion: Question) : Question?
    suspend fun getQuestion(id: Int) : Question?
    suspend fun allQuestions(): List<Question>
    suspend fun clearQuestions()
    suspend fun updateQuestion(id: Int, updatedQuestion: Question)
}