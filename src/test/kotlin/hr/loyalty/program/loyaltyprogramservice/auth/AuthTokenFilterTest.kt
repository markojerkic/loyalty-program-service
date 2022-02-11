package hr.loyalty.program.loyaltyprogramservice.auth

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService
import hr.loyalty.program.loyaltyprogramservice.auth.util.AuthTokenFilter
import hr.loyalty.program.loyaltyprogramservice.auth.util.JwtUtils
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuthTokenFilterTest(
    @Mock val jwtUtils: JwtUtils,
    @Mock val userManagementService: UserManagementService,
    @InjectMocks val authTokenFilter: AuthTokenFilter
) {

    @Test
    fun testDoFilterInternal() {
        val loginReq = LoginRequest("markojerkic@gmail.com", "pass")
        val jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTY0NDU5NjEyNCwiZXhwIjoxNjc2MTMyMTI0LCJhdWQiOiJ3d3cuZXhhbXBsZS5jb20iLCJzdWIiOiJtYXJrb2plcmtpYzI2NkBnbWFpbC5jb20iLCJHaXZlbk5hbWUiOiJKb2hubnkiLCJTdXJuYW1lIjoiUm9ja2V0IiwiRW1haWwiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiUm9sZSI6WyJNYW5hZ2VyIiwiUHJvamVjdCBBZG1pbmlzdHJhdG9yIl19.bN8j2pDVkBCB7Y6brVVQj85iO_UstXMP6hmeHdhiKKc"

        Mockito.`when`(userManagementService.loadUserByUsername(any())).thenReturn(
            User(UUID.randomUUID(), loginReq.email, "Marko", "Jerkić", loginReq.password)
        )

        authTokenFilter.doFilter(any(), any(), any())
    }
}