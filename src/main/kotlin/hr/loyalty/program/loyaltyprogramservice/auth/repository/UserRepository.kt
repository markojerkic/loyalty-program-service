package hr.loyalty.program.loyaltyprogramservice.auth.repository

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findUserByEmail(email: String): Optional<User>
}