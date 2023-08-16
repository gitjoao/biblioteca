package com.biblioteca.controller.exception


class BadRequestException(val errorCode: String): Exception() {
}