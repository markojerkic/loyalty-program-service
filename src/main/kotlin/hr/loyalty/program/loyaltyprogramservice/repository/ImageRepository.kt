package hr.loyalty.program.loyaltyprogramservice.repository

import hr.loyalty.program.loyaltyprogramservice.model.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ImageRepository: JpaRepository<Image, UUID> {
}