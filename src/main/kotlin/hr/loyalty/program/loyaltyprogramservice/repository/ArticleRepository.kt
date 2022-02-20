package hr.loyalty.program.loyaltyprogramservice.repository

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository: JpaRepository<Article, UUID> {
    fun findAllByStatusOrderByNameAsc(status: PublishedStatus): List<Article>
}