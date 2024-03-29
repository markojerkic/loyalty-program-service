package hr.loyalty.program.loyaltyprogramservice.controller

import hr.loyalty.program.loyaltyprogramservice.model.dto.article.ArticlePatchDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.article.ArticlePostDto
import hr.loyalty.program.loyaltyprogramservice.service.ArticleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("article")
@Validated
class ArticleController(private val articleService: ArticleService) {
    @GetMapping
    fun getAllArticles() = articleService.getAllArticles()

    @GetMapping("published")
    fun getAllPublishedArticles() = articleService.getAllPublishedArticles()

    @GetMapping("{id}")
    fun getArticleById(@PathVariable id: UUID) = articleService.getArticleById(id)

    @PostMapping
    fun addNewArticle(articlePostDTO: ArticlePostDto) = articleService.saveArticle(articlePostDTO)

    @PatchMapping("{id}")
    fun updateArticle(@PathVariable id: UUID, articlePatchDTO: ArticlePatchDto) =
        articleService.updateArticle(id, articlePatchDTO)

    @PutMapping("{id}")
    fun toggleStatus(@PathVariable id: UUID) = articleService.toggleStatus(id)
}