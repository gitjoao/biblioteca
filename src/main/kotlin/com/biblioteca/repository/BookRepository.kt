package com.biblioteca.repository

import com.biblioteca.enums.BookStatus
import com.biblioteca.model.BookModel
import com.biblioteca.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository: JpaRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
    //fun findAll(pageable: Pageable): Page<BookModel>
}