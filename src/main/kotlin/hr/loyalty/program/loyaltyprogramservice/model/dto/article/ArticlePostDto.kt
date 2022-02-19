package hr.loyalty.program.loyaltyprogramservice.model.dto.article

import org.springframework.web.multipart.MultipartFile

data class ArticlePostDto(
    val name: String,
    val description: String,
    val image: MultipartFile?
)
