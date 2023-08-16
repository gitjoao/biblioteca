package com.biblioteca.service

import com.biblioteca.controller.exception.NotFoundException
import com.biblioteca.enums.BookErrors
import com.biblioteca.enums.BookStatus
import com.biblioteca.model.BookModel
import com.biblioteca.model.CustomerModel
import com.biblioteca.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun getById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException(BookErrors.BOOK_NOT_FOUND.code) }
    }

    fun delete(id: Int, ) {
        val book = getById(id)

        book.status = BookStatus.CANCELADO

        bookRepository.save(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
       val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

}
