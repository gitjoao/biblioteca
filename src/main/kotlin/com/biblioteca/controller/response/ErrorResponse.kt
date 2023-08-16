package com.biblioteca.controller.response

data class ErrorResponse (
    val httpCode: Int,
    val internalCode: String,
    val errors: List<FieldErrorResponse>?
)