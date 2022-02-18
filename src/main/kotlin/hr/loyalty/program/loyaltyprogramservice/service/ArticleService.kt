package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticlePatchDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticlePostDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticleResponseDto
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import hr.loyalty.program.loyaltyprogramservice.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ArticleService(
    val articleRepository: ArticleRepository,
    val imageService: ImageService
) {

    fun getAllArticles(): List<ArticleResponseDto> {
        val articles = articleRepository.findAll()

        return articles.map { article -> mapArticleDto(article) }
    }

    fun getArticleById(id: UUID): ArticleResponseDto {
        val article = articleRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return mapArticleDto(article)
    }

    private fun mapArticleDto(article: Article): ArticleResponseDto {
        val imageUri = if (article.imageUri != null) "/image/${article.imageUri}" else null
        return ArticleResponseDto(
            article.id,
            article.name,
            article.description,
            imageUri,
            article.status
        )
    }

    fun saveArticle(articlePostDto: ArticlePostDto): Article {
        val imageUri = if (articlePostDto.image != null) imageService.saveImage(articlePostDto.image) else null

        return articleRepository.save(
            Article(
                UUID.randomUUID(),
                articlePostDto.name,
                articlePostDto.description,
                imageUri,
                PublishedStatus.DRAFT
            )
        )
    }

    fun updateArticle(id: UUID, dto: ArticlePatchDto): ArticleResponseDto {
        val article = articleRepository.findById(id)
            .orElseThrow{ResponseStatusException(HttpStatus.NOT_FOUND)}

        article.status = PublishedStatus.DRAFT
        article.name = dto.name
        article.description = dto.description

        if (dto.removeImage && article.imageUri != null) {
            imageService.deleteImage(article.imageUri!!)
            article.imageUri = null
        } else if (dto.image != null) {
            article.imageUri = imageService.replaceImage(article.imageUri, dto.image)
        }

        return mapArticleDto(articleRepository.save(article))
    }
}