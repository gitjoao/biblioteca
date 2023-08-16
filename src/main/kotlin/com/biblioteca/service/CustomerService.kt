package com.biblioteca.service

import com.biblioteca.controller.exception.NotFoundException
import com.biblioteca.enums.CustomerErrors
import com.biblioteca.enums.CustomerStatus
import com.biblioteca.model.CustomerModel
import com.biblioteca.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository, val bookService: BookService) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(CustomerErrors.CUSTOMER_NOT_FOUND.code) }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
       /* if (!customerRepository.existsById(id)) {
            throw Exception()
        }
        */
        val customer = getById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.DELETADO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
       return !customerRepository.existsByEmail(email)
    }
}