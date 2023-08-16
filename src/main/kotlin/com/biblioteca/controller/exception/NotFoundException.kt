package com.biblioteca.controller.exception

class NotFoundException(
    val errorCode: String,
): Exception() {
}