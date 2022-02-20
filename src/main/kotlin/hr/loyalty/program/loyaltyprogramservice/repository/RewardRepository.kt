package hr.loyalty.program.loyaltyprogramservice.repository

import hr.loyalty.program.loyaltyprogramservice.model.Reward
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RewardRepository: JpaRepository<Reward, UUID> {
    fun findAllByArticle_Id(id: UUID): List<Reward>
}