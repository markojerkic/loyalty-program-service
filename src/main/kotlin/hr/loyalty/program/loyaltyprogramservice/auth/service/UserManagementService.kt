package hr.loyalty.program.loyaltyprogramservice.auth.service

import hr.loyalty.program.loyaltyprogramservice.auth.error.UserExistsException
import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.RegisterRequest
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.RegisterResponse
import hr.loyalty.program.loyaltyprogramservice.auth.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserManagementService(val userRepo: UserRepository,
                            val passwordEncoder: PasswordEncoder): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepo.findUserByEmail(username).orElseThrow()
    }

    fun register(registerRequest: RegisterRequest): RegisterResponse {
        if (userRepo.existsByEmail(registerRequest.email)) {
            throw UserExistsException("User with email ${registerRequest.email} already exists")
        }

        val user = User(
            UUID.randomUUID(),
            registerRequest.email,
            registerRequest.firstName,
            registerRequest.lastName,
            passwordEncoder.encode(registerRequest.password)
        )

        return mapToRegisterResponse(userRepo.save(user))
    }

    private fun mapToRegisterResponse(user: User): RegisterResponse {
        return RegisterResponse(user.email, user.firstName, user.lastName)
    }
}