package com.thelazybattley.ext


private const val SEPARATOR = "*,*"
fun List<String>.toQuizString(): String = run {
    joinToString(separator = SEPARATOR )
}

fun String.toQuizList() : List<String> = run {
    split(SEPARATOR)
}