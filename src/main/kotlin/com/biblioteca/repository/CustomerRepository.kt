package com.biblioteca.repository

import com.biblioteca.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<CustomerModel, Int> {

    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
}