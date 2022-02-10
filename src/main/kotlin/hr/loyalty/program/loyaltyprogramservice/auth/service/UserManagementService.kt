package hr.loyalty.program.loyaltyprogramservice.auth.service

import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.JwtToken
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.repository.UserRepository
import hr.loyalty.program.loyaltyprogramservice.auth.util.JwtUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserManagementService(val userRepo: UserRepository,
                            val authManager: AuthenticationManager,
                            val jwtUtil: JwtUtils): UserDetailsService {

    fun login(loginRequest: LoginRequest): JwtToken {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(
            loginRequest.email, loginRequest.password
        ))

        SecurityContextHolder.getContext().authentication = auth

        val token = jwtUtil.generateToken(auth)

        return JwtToken(loginRequest.email, token)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepo.findUserByEmail(username).orElseThrow()
    }
}