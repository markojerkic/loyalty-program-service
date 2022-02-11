package hr.loyalty.program.loyaltyprogramservice.auth.configuration

import hr.loyalty.program.loyaltyprogramservice.auth.error.UserExistsException
import hr.loyalty.program.loyaltyprogramservice.auth.model.api.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MvcExceptionHandler {

    @ExceptionHandler(UserExistsException::class)
    fun handleUserExists(e: UserExistsException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            ErrorResponse(e.message!!)
        )
    }
}