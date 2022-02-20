package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.model.Article
import hr.loyalty.program.loyaltyprogramservice.model.Reward
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import hr.loyalty.program.loyaltyprogramservice.repository.ArticleRepository
import hr.loyalty.program.loyaltyprogramservice.repository.RewardRepository
import org.springframework.stereotype.Service

@Service
class PublishedStatusService(
    private val rewardRepository: RewardRepository,
    private val articleRepository: ArticleRepository
) {

    fun toggleStatus(reward: Reward): PublishedStatus {
        reward.status = if (reward.status == PublishedStatus.PUBLISHED)
            PublishedStatus.DRAFT else PublishedStatus.PUBLISHED

        return rewardRepository.save(reward).status
    }

    fun toggleStatus(article: Article): PublishedStatus {
        article.status = if (article.status == PublishedStatus.PUBLISHED)
            PublishedStatus.DRAFT else PublishedStatus.PUBLISHED

        if (article.status == PublishedStatus.DRAFT) {
            rewardRepository.findAllByArticle_Id(article.id).forEach { reward -> draftReward(reward) }
        }

        return articleRepository.save(article).status
    }

    private fun draftReward(reward: Reward): PublishedStatus {
        reward.status = PublishedStatus.DRAFT

        return rewardRepository.save(reward).status
    }
}