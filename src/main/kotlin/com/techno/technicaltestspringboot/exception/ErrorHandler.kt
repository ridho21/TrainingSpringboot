package com.techno.technicaltestspringboot.exception

import com.techno.technicaltestspringboot.dto.response.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception
import java.lang.reflect.Method

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = BaseResponse("F", "Something Went Wrong!!", errors)
        return ResponseEntity.badRequest().body(result)
    }
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any> {
        val result = BaseResponse("F", "Something Went Wrong!!", exception.message!!)
        return ResponseEntity.badRequest().body(result)
    }

}