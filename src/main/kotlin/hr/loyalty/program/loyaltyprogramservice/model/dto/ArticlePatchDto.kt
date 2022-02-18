package hr.loyalty.program.loyaltyprogramservice.model.dto

import org.springframework.web.multipart.MultipartFile

data class ArticlePatchDto(
    val name: String,
    val description: String,
    val image: MultipartFile?,
    val removeImage: Boolean = false
)