package hr.loyalty.program.loyaltyprogramservice.auth.controller

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.RegisterRequest
import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
@Validated
class UserManagementController(val userManagementService: UserManagementService) {
    @PostMapping
    fun addNewUser(@RequestBody registerRequest: RegisterRequest): User {
        return userManagementService.register(registerRequest)
    }
}