package com.algaworks.osworks.domain.model

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @ManyToOne
        var serviceOrder: ServiceOrder?,

        var description: String?,
        var sendDate: OffsetDateTime?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}