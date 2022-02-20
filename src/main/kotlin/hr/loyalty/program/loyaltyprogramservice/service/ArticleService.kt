package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.dto.article.ArticlePatchDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.article.ArticlePostDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.article.ArticleResponseDto
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus.DRAFT
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus.PUBLISHED
import hr.loyalty.program.loyaltyprogramservice.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val imageService: ImageService
) {

    fun getAllArticles(): List<ArticleResponseDto> {
        val articles = articleRepository.findAll()

        return articles.map { article -> mapArticleDto(article) }
    }

    fun findArticleById(id: UUID): Article =
        articleRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun getArticleById(id: UUID): ArticleResponseDto {
        val article = findArticleById(id)

        return mapArticleDto(article)
    }

    fun resolveImageUri(imageUri: String?): String? {
        return if (imageUri != null) "/image/${imageUri}" else null
    }

    private fun mapArticleDto(article: Article): ArticleResponseDto {
        val imageUri = resolveImageUri(article.imageUri)
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
                DRAFT
            )
        )
    }

    fun updateArticle(id: UUID, dto: ArticlePatchDto): ArticleResponseDto {
        val article = findArticleById(id)

        article.status = DRAFT
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

    fun toggleStatus(id: UUID): PublishedStatus {
        val article = findArticleById(id)
        article.status = if (article.status == PUBLISHED) DRAFT else PUBLISHED
        return articleRepository.save(article).status
    }

    fun getAllPublishedArticles(): List<ArticleResponseDto> {
        return articleRepository.findAllByStatusOrderByNameAsc(PUBLISHED).map { article -> mapArticleDto(article) }
    }
}