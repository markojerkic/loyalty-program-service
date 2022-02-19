package hr.loyalty.program.loyaltyprogramservice.model.dto.reward

import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import java.util.UUID

data class RewardResponseDto(
    val id: UUID,
    val articleId: UUID,
    val articleName: String,
    val articleImageUri: String?,
    val requiredPoints: Number,
    val status: PublishedStatus
)
