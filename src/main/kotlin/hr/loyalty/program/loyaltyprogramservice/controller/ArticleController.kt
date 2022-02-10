package hr.loyalty.program.loyaltyprogramservice.controller

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.service.ArticleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("article")
@Validated
class ArticleController(val articleService: ArticleService) {
    @GetMapping
    fun getAllArticles() = articleService.getAllArticles()
    @PostMapping
    fun addNewArticle(@RequestBody article: Article) = articleService.saveArticle(article)
}