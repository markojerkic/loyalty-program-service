package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.ArticleRepository
import hr.loyalty.program.loyaltyprogramservice.model.Article
import org.springframework.stereotype.Service

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getAllArticles(): List<Article> = articleRepository.findAll()

    fun saveArticle(article: Article) = articleRepository.save(article)
}