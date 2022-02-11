package hr.loyalty.program.loyaltyprogramservice.auth

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.service.auth.AuthService
import hr.loyalty.program.loyaltyprogramservice.auth.util.JwtUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

class LoginTest(
) {

    private val authManager: AuthenticationManager = mockk()
    private val jwtUtils: JwtUtils = mockk()
    private val authService = AuthService(authManager, jwtUtils)

    @Test
    fun testLogin() {
        val loginReq = LoginRequest("markojerkic@gmail.com", "pass")

        every {
            authManager.authenticate(any())
        } returns returnAuth(loginReq)

        every {
            jwtUtils.generateToken(any())
        } returns "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTY0NDU5NjEyNCwiZXhwIjoxNjc2MTMyMTI0LCJhdWQiOiJ3d3cuZXhhbXBsZS5jb20iLCJzdWIiOiJtYXJrb2plcmtpYzI2NkBnbWFpbC5jb20iLCJHaXZlbk5hbWUiOiJKb2hubnkiLCJTdXJuYW1lIjoiUm9ja2V0IiwiRW1haWwiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiUm9sZSI6WyJNYW5hZ2VyIiwiUHJvamVjdCBBZG1pbmlzdHJhdG9yIl19.bN8j2pDVkBCB7Y6brVVQj85iO_UstXMP6hmeHdhiKKc"

        val token = authService.login(loginReq)

        assertNotNull(token)
    }

    private fun returnAuth(loginReq: LoginRequest): Authentication {
        val user = User(UUID.randomUUID(), loginReq.email, "Marko", "Jerkić", loginReq.password)
        val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))

        return UsernamePasswordAuthenticationToken(
            user, null,
            authorities
        )
    }
}