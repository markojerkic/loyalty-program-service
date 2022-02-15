package hr.loyalty.program.loyaltyprogramservice.controller

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.ArticlePostDTO
import hr.loyalty.program.loyaltyprogramservice.service.ArticleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("article")
@Validated
class ArticleController(val articleService: ArticleService) {
    @GetMapping
    fun getAllArticles() = articleService.getAllArticles()
    @PostMapping
    fun addNewArticle(articlePostDTO: ArticlePostDTO) = articleService.saveArticle(articlePostDTO)
}