package hr.loyalty.program.loyaltyprogramservice.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtUtils(@Value("{jwt.secret}") private val jwtSecret: String) {
    private val algorithm: Algorithm = Algorithm.HMAC256(jwtSecret)
    private val verifier: JWTVerifier

    init {
        verifier = JWT.require(algorithm)
            .withIssuer("auth0")
            .build()
    }

    fun generateToken(auth: Authentication): String {
        val user = auth.principal as User
        return JWT.create()
            .withIssuer("auth0")
            .withSubject(user.username)
            .sign(algorithm)
    }

    fun decodeJwt(jwt: String): DecodedJWT = verifier.verify(jwt)
}