package hr.loyalty.program.loyaltyprogramservice.model.dto.reward

import java.util.UUID

data class RewardRequestDto(
    val articleId: UUID,
    val requiredPoints: Number
)