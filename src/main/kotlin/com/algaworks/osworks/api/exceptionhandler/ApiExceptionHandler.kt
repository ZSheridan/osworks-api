package com.algaworks.osworks.api.exceptionhandler

import com.algaworks.osworks.domain.exception.BusinessException
import com.algaworks.osworks.domain.exception.EntityNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime

@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val problem = Problem(status.value(), ex.message!!, OffsetDateTime.now())
        return handleExceptionInternal(ex, problem, HttpHeaders(), status, request)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val problem = Problem(status.value(), ex.message!!, OffsetDateTime.now())
        return handleExceptionInternal(ex, problem, HttpHeaders(), status, request)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders, status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {

        val campos = mutableListOf<Field>()
        for (error in ex.bindingResult.allErrors) {
            campos.add(Field((error as FieldError).field, error.defaultMessage))
        }

        val problem = Problem(
                status.value(),
                "One or more parameters are invalid. Try again.",
                OffsetDateTime.now(),
                campos)

        return super.handleExceptionInternal(ex, problem, headers, status, request)
    }

}