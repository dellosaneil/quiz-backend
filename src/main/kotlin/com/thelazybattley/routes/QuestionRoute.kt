package com.thelazybattley.routes

import com.thelazybattley.db.dao.QuestionDAOFacade
import com.thelazybattley.db.dao.impl.QuestionDAOFacadeImpl
import com.thelazybattley.models.Question
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.questionsRouting() {
    val dao: QuestionDAOFacade = QuestionDAOFacadeImpl()

    route("/question") {
        get {
            val id = call.request.queryParameters["id"]?.toIntOrNull() ?: return@get
            val question = dao.getQuestion(id) ?: return@get
            call.respond(
                status = HttpStatusCode.OK,
                message = question
            )
        }
        post("/insert") {
            val questionParam = call.receive<Question>()
            val addedQuestion = dao.insertQuestion(
                newQuestion = questionParam
            )
            if (addedQuestion == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Question cannot be added"
                )
                return@post
            }
            call.respond(
                status = HttpStatusCode.OK,
                message = addedQuestion
            )
        }
        get("/all") {
            val questions = dao.allQuestions()
            call.respond(
                status = HttpStatusCode.OK,
                message = questions
            )
        }
        patch {
            val id = call.request.queryParameters["id"]?.toIntOrNull() ?: return@patch
            val updatedQuestion = call.receive<Question>()
            dao.updateQuestion(id = id, updatedQuestion = updatedQuestion)
            call.respond(
                status = HttpStatusCode.OK,
                message = "Question Updated"
            )
        }
    }
}