package com.biblioteca.controller.response

import com.biblioteca.enums.BookStatus
import com.biblioteca.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus?
)