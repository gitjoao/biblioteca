package com.biblioteca.controller.request

import com.biblioteca.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostCustomerRequest (
    @field: NotEmpty(message = "Nome não pode ser vazio")
    var name: String,

    @field: Email(message = "Email deve ser valido")
    @EmailAvailable
    var email: String
)