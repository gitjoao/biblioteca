package com.biblioteca.extension

import com.biblioteca.controller.request.PostBookRequest
import com.biblioteca.controller.request.PostCustomerRequest
import com.biblioteca.controller.request.PutBookRequest
import com.biblioteca.controller.request.PutCustomerRequest
import com.biblioteca.controller.response.BookResponse
import com.biblioteca.controller.response.CustomerResponse
import com.biblioteca.enums.BookStatus
import com.biblioteca.enums.CustomerStatus
import com.biblioteca.model.BookModel
import com.biblioteca.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(customer: CustomerModel): CustomerModel {
    return CustomerModel(
        id = customer.id,
        name = this.name,
        email = this.email,
        status = customer.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(book: BookModel): BookModel {
    return BookModel(
        id = book.id,
        name= this.name ?: book.name,
        price = this.price ?: book.price,
        status = book.status,
        customer = book.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return  BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}