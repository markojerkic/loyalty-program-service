package hr.loyalty.program.loyaltyprogramservice.model

import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class Reward(
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID,
    @Column(nullable = false)
    var requiredPoints: Number,
    @Column
    @Enumerated(EnumType.STRING)
    var status: PublishedStatus,
    @ManyToOne
    @Cascade(CascadeType.DELETE)
    var article: Article
)
