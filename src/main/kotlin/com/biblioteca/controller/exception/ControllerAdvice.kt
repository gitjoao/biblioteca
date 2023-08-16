package com.biblioteca.controller.exception

import com.biblioteca.controller.response.ErrorResponse
import com.biblioteca.controller.response.FieldErrorResponse
import com.biblioteca.enums.CustomerErrors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
       val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
           ex.errorCode,
            null)

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.errorCode,
            null)

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "GEN-000",
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "Invalid", it.field) })
        return ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY)
    }


}