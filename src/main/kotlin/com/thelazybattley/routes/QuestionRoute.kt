package com.thelazybattley.routes

import com.thelazybattley.db.dao.QuestionDAOFacade
import com.thelazybattley.db.dao.impl.QuestionDAOFacadeImpl
import com.thelazybattley.db.dbQuery
import com.thelazybattley.db.entity.QuestionEntity
import com.thelazybattley.mapper.toEntity
import com.thelazybattley.payloads.QuestionPayload
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
            val question = dbQuery { dao.getQuestion(id) } ?: return@get
            call.respond(
                status = HttpStatusCode.OK,
                message = question
            )
        }
        post("/insert") {
            val questionParam = call.receive<QuestionPayload>()
            val question = dao.insertQuestion(
                newQuestion = questionParam.toEntity
            )
            if (question == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Question cannot be added"
                )
                return@post
            }
            call.respond(
                status = HttpStatusCode.OK,
                message = question
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
            val updatedQuestion = call.receive<QuestionEntity>()
            dao.updateQuestion(id = id, updatedQuestion = updatedQuestion)
            call.respond(
                status = HttpStatusCode.OK,
                message = "Question Updated"
            )
        }
    }
}