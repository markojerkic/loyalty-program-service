package hr.loyalty.program.loyaltyprogramservice.controller

import hr.loyalty.program.loyaltyprogramservice.model.dto.reward.RewardRequestDto
import hr.loyalty.program.loyaltyprogramservice.service.RewardService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("reward")
class RewardController(
    private val rewardService: RewardService
) {

    @GetMapping
    fun getAllRewards() = rewardService.getAllRewards()

    @GetMapping("{id}")
    fun getRewardById(@PathVariable id: UUID) = rewardService.getRewardById(id)

    @PostMapping
    fun saveNewReward(@RequestBody request: RewardRequestDto) = rewardService.addNewReward(request)

    @PatchMapping("{id}")
    fun updateReward(@PathVariable id: UUID, @RequestBody request: RewardRequestDto) =
        rewardService.updateReward(id, request)

    @PutMapping("{id}")
    fun toggleStatus(@PathVariable id: UUID) = rewardService.toggleStatus(id)
}