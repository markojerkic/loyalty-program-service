package hr.loyalty.program.loyaltyprogramservice

import hr.loyalty.program.loyaltyprogramservice.auth.model.User
import hr.loyalty.program.loyaltyprogramservice.auth.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class LoyaltyProgramServiceApplication {
    @Bean
    fun init(repository: UserRepository) = CommandLineRunner {
        repository.save(User(UUID.randomUUID(), "markojerkic266@gmail.com", "Marko", "Jerkić",
            "\$2a\$12\$aNfNFYaPUMT55A/O0zfDyu.NsOjxgXs34gWR3XWt0SSklBeD4Q0s6"
        ))
    }
}

fun main(args: Array<String>) {
    runApplication<LoyaltyProgramServiceApplication>(*args)
}
