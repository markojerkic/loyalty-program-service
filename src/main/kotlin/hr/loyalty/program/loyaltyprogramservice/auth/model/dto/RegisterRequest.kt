package hr.loyalty.program.loyaltyprogramservice.auth.model.dto

data class RegisterRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)
