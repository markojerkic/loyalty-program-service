package hr.loyalty.program.loyaltyprogramservice

import hr.loyalty.program.loyaltyprogramservice.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository: JpaRepository<Article, UUID> {
}