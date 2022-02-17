package hr.loyalty.program.loyaltyprogramservice.model.dto

import java.util.UUID

data class ArticleResponseDto(
    val id: UUID,
    val name: String,
    val description: String,
    val imageUrl: String?
)
