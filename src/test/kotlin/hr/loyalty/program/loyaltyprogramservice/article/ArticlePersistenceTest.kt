package hr.loyalty.program.loyaltyprogramservice.article

import hr.loyalty.program.loyaltyprogramservice.ArticleRepository
import hr.loyalty.program.loyaltyprogramservice.model.Article
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class ArticlePersistenceTest(@Autowired val articleRepository: ArticleRepository) {
    @Test
    fun testArticleSave() {
        val article = Article(UUID.randomUUID(), "article", "description")
        val savedInstance = articleRepository.save(article)

        assertNotNull(savedInstance)
    }
    @Test
    fun testFetchAllArticles() {
        val articles = articleRepository.findAll()

        assertNotNull(articles)
    }
}