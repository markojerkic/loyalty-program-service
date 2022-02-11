package hr.loyalty.program.loyaltyprogramservice.auth.controller

import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.JwtToken
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.service.auth.AuthService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
@Validated
class AuthController(val authService: AuthService) {
    @PostMapping
    fun login(@RequestBody loginRequest: LoginRequest): JwtToken {
        return authService.login(loginRequest)
    }
}