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
            val question = dao.getQuestion(1) ?: Question(question = "emptyQ", answer = "emptyA", id = 1)
            call.respond(
                HttpStatusCode.OK,
                question
            )
        }
        post("/insert") {
            val question = call.receive<Question>()
            val q = dao.insertQuestion(
                newQuestion = question
            )
            call.respond(
                status = HttpStatusCode.OK,
                message = q!!
            )
        }
    }
}