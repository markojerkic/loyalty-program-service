package hr.loyalty.program.loyaltyprogramservice.auth.configuration.oauth

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
    private val passwordEncoder: PasswordEncoder,
    @Value("\${security.oauth2.client.client-id}")
    private val clientId: String,
    @Value("\${security.oauth2.client.client-secret}")
    private val clientSecret: String):
    AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
            .inMemory()
            .withClient(clientId)
            .secret(passwordEncoder.encode(clientSecret))
            .scopes("resource:read", "password")
            .authorizedGrantTypes("authorization_code")
            .redirectUris("http://localhost:3000/api/auth/callback/loyalty")
    }

}