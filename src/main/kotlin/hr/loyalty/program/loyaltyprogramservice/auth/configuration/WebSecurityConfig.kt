package hr.loyalty.program.loyaltyprogramservice.auth.configuration

import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService
import hr.loyalty.program.loyaltyprogramservice.auth.util.AuthEntryPointJwt
import hr.loyalty.program.loyaltyprogramservice.auth.util.AuthTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class WebSecurityConfig(private val authTokenFilter: AuthTokenFilter,
                        private val unauthorizedHandler: AuthEntryPointJwt,
                        private val userManagementService: UserManagementService,
                        private val passwordEncoder: PasswordEncoder
): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.cors()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/info")
                    .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/**")
                    .hasAuthority("SCOPE_write")
                .anyRequest().authenticated()
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(this.userManagementService).passwordEncoder(passwordEncoder);
    }
}