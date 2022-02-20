package hr.loyalty.program.loyaltyprogramservice.service

import hr.loyalty.program.loyaltyprogramservice.model.Reward
import hr.loyalty.program.loyaltyprogramservice.model.dto.reward.RewardRequestDto
import hr.loyalty.program.loyaltyprogramservice.model.dto.reward.RewardResponseDto
import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import hr.loyalty.program.loyaltyprogramservice.repository.RewardRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class RewardService(
    private val rewardRepository: RewardRepository,
    private val articleService: ArticleService,
    private val publishedStatusService: PublishedStatusService
) {

    private fun mapToDto(reward: Reward): RewardResponseDto {
        return RewardResponseDto(
            reward.id,
            reward.article.id,
            reward.article.name,
            articleService.resolveImageUri(reward.article.imageUri),
            reward.requiredPoints,
            reward.status
        )
    }

    fun addNewReward(request: RewardRequestDto): RewardResponseDto {
        val article = articleService.findArticleById(request.articleId)
        val reward = Reward(
            UUID.randomUUID(),
            request.requiredPoints,
            PublishedStatus.DRAFT,
            article
        )

        return mapToDto(rewardRepository.save(reward))
    }

    fun getAllRewards(): List<RewardResponseDto> {
        return rewardRepository.findAll().map { reward -> mapToDto(reward) }
    }

    private fun findRewardById(id: UUID): Reward {
        return rewardRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun getRewardById(id: UUID): RewardResponseDto {
        return mapToDto(findRewardById(id))
    }

    fun updateReward(id: UUID, request: RewardRequestDto): RewardResponseDto {
        val reward = findRewardById(id)

        reward.article = articleService.findArticleById(request.articleId)
        reward.requiredPoints = request.requiredPoints
        reward.status = PublishedStatus.DRAFT
        return mapToDto(rewardRepository.save(reward))
    }

    fun toggleStatus(id: UUID): PublishedStatus {
        val reward = findRewardById(id)

        return publishedStatusService.toggleStatus(reward)
    }
}