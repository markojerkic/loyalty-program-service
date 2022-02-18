package hr.loyalty.program.loyaltyprogramservice.model.dto

import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import java.util.UUID

data class ArticleResponseDto(
    val id: UUID,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val status: PublishedStatus
)
