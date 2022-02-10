package hr.loyalty.program.loyaltyprogramservice.auth

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.model.dto.LoginRequest
import hr.loyalty.program.loyaltyprogramservice.auth.repository.UserRepository
import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService
import hr.loyalty.program.loyaltyprogramservice.auth.util.JwtUtils
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.security.authentication.AuthenticationManager
import java.util.*
import java.util.Optional.of

@RunWith(MockitoJUnitRunner::class)
class LoginTest(@Mock val userRepository: UserRepository,
                @Mock val authManager: AuthenticationManager,
                @Mock val jwtUtils: JwtUtils,
                @InjectMocks val userManagementService: UserManagementService
) {

    @Test
    fun testLogin() {
        val loginReq = LoginRequest("markojerkic@gmail.com", "pass")

        Mockito.`when`(userRepository.findUserByEmail(loginReq.email)).thenReturn(
            of(User(UUID.randomUUID(), loginReq.email, "Marko", "Jerkić", loginReq.password))
        )

        val token = userManagementService.login(loginReq)

        assertNotNull(token)
    }
}