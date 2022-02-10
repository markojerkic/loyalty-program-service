package hr.loyalty.program.loyaltyprogramservice.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import hr.loyalty.program.loyaltyprogramservice.auth.model.User;
import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final UserManagementService userManagementService;

    public JwtUtils(@Value("{jwt.secret}") String jwtSecret, UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
        this.algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
    }

    public String generateToken(Authentication auth) {
        User user = (User) auth.getPrincipal();

        return JWT.create()
                .withIssuer("auth0")
                .withClaim("username", user.getUsername())
                .sign(algorithm);
    }

    public UserDetails getUserFromJWT(String jwt) {
        DecodedJWT decodedJWT = this.decodeJwt(jwt);
        return this.userManagementService.loadUserByUsername(decodedJWT.getClaim("username").asString());
    }

    private DecodedJWT decodeJwt(String jwt) {
        return this.verifier.verify(jwt);
    }
}
