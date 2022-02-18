package hr.loyalty.program.loyaltyprogramservice.model

import hr.loyalty.program.loyaltyprogramservice.model.enum.PublishedStatus
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class Article (
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID,
    @Column
    var name: String,
    @Column
    var description: String,
    @Column
    var imageUri: String?,
    @Column
    @Enumerated(EnumType.STRING)
    var status: PublishedStatus
//    @OneToOne(targetEntity = Image::class, cascade = arrayOf(CascadeType.DETACH))
//    var image: Image?
    
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Article

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}