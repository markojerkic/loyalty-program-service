package hr.loyalty.program.loyaltyprogramservice.controller

import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticlePatchDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.ArticlePostDto
import hr.loyalty.program.loyaltyprogramservice.service.ArticleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("article")
@Validated
@CrossOrigin("*")
class ArticleController(val articleService: ArticleService) {
    @GetMapping
    fun getAllArticles() = articleService.getAllArticles()

    @GetMapping("{id}")
    fun getArticleById(@PathVariable id: UUID) = articleService.getArticleById(id)

    @PostMapping
    fun addNewArticle(articlePostDTO: ArticlePostDto) = articleService.saveArticle(articlePostDTO)

    @PatchMapping("{id}")
    fun updateArticle(@PathVariable id: UUID, articlePatchDTO: ArticlePatchDto) =
        articleService.updateArticle(id, articlePatchDTO)
}