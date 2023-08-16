package com.biblioteca.controller.response

import com.biblioteca.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus?,
)