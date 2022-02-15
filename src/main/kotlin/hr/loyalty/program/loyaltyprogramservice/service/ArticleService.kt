package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.ArticleRepository
import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.ArticlePostDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getAllArticles(): List<Article> = articleRepository.findAll()

    fun saveArticle(articlePostDto: ArticlePostDTO): Article {
        println(articlePostDto.image.name)
        val article = Article(UUID.randomUUID(), articlePostDto.name, articlePostDto.description)
        return articleRepository.save(article)
    }
}