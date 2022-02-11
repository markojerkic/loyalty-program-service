package hr.loyalty.program.loyaltyprogramservice.auth.util

import hr.loyalty.program.loyaltyprogramservice.auth.service.UserManagementService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(private val jwtUtils: JwtUtils,
                    private val userManagementService: UserManagementService) :
    OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val user = userManagementService.loadUserByUsername(
            jwtUtils.decodeJwt(
                parseJwt(request)!!
            ).getClaim("username").asString()
        )
        val auth = UsernamePasswordAuthenticationToken(
            user, null,
            user.authorities
        )
        auth.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            headerAuth.substring(7)
        } else null
    }
}