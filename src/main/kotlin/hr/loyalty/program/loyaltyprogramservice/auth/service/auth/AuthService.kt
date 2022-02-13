package hr.loyalty.program.loyaltyprogramservice.auth.service.auth

import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.JwtToken
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.util.JwtUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthService(
    val authManager: AuthenticationManager,
    val jwtUtil: JwtUtils
) {

    fun login(loginRequest: LoginRequest): JwtToken {
        val auth = authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email, loginRequest.password
            )
        )

        SecurityContextHolder.getContext().authentication = auth

        val token = jwtUtil.generateToken(auth)

        return JwtToken(loginRequest.email, token)
    }

}