package hr.loyalty.program.loyaltyprogramservice.model

import org.springframework.web.multipart.MultipartFile

data class ArticlePostDTO(
    val name: String,
    val description: String,
    val image: MultipartFile
)
