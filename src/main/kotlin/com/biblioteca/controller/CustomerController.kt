package com.biblioteca.controller

import com.biblioteca.controller.request.PostCustomerRequest
import com.biblioteca.controller.request.PutCustomerRequest
import com.biblioteca.controller.response.CustomerResponse
import com.biblioteca.extension.toCustomerModel
import com.biblioteca.extension.toResponse
import com.biblioteca.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("customer")
class CustomerController(val customerService: CustomerService) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
       return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        return customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Int): CustomerResponse {
        return customerService.getById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val oldCustomer = customerService.getById(id)
        customerService.update(customer.toCustomerModel(oldCustomer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}