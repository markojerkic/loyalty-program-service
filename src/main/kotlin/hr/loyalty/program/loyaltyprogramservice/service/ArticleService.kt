package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticlePostDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticleResponseDto
import hr.loyalty.program.loyaltyprogramservice.repository.ArticleRepository
import org.springframework.stereotype.Service
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
        val article = articleRepository.findById(id)
        if (article.isEmpty) {
            throw IllegalArgumentException("Article with id $id is not found")
        }

        return mapArticleDto(article.get())
    }

    private fun mapArticleDto(article: Article): ArticleResponseDto {
        val imageUri = if (article.imageUri != null) "/image/${article.imageUri}" else null
        return ArticleResponseDto(
            article.id,
            article.name,
            article.description,
            imageUri
        )
    }

    fun saveArticle(articlePostDto: ArticlePostDto): Article {
        val imageUri = if (articlePostDto.image != null) imageService.saveImage(articlePostDto.image) else null

        return articleRepository.save(
            Article(
                UUID.randomUUID(),
                articlePostDto.name,
                articlePostDto.description,
                imageUri
            )
        )
    }
}