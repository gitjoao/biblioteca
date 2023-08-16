package com.biblioteca.controller

import com.biblioteca.controller.request.PostBookRequest
import com.biblioteca.controller.request.PutBookRequest
import com.biblioteca.controller.response.BookResponse
import com.biblioteca.extension.toBookModel
import com.biblioteca.extension.toResponse
import com.biblioteca.service.BookService
import com.biblioteca.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid book: PostBookRequest) {
      val customer = customerService.getById(book.customerId)
        bookService.create(book.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable):Page<BookResponse>{
        return bookService.findActives(pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse {
        return bookService.getById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.getById(id)
        bookService.update(book.toBookModel(bookSaved))
    }
}