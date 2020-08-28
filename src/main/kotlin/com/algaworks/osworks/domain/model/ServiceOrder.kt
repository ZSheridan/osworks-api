package com.algaworks.osworks.domain.model

import com.algaworks.osworks.domain.exception.BusinessException
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@JsonInclude(Include.NON_NULL)
class ServiceOrder (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @ManyToOne
        var client: Client?,

        var description: String?,
        var price: BigDecimal?,

        @Enumerated(EnumType.STRING)
        var status: ServiceOrderStatus?,

        var openingDate: OffsetDateTime?,
        var finishingDate: OffsetDateTime? = null,

        @OneToMany(mappedBy = "serviceOrder")
        var comments: MutableList<Comment> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ServiceOrder

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    private fun canBeClosedOrCancelled() = status == ServiceOrderStatus.OPEN

    fun close() {
        if (!canBeClosedOrCancelled()) {
            throw BusinessException("Service Order cannot be closed.")
        }
        status = ServiceOrderStatus.CLOSED
        finishingDate = OffsetDateTime.now()
    }

    fun cancel() {
        if (!canBeClosedOrCancelled()) {
            throw BusinessException("Service Order cannot be cancelled.")
        }
        status = ServiceOrderStatus.CANCELLED
    }

    fun canAddComment() {
        if (!canBeClosedOrCancelled()) {
            throw BusinessException("Service Order is not open. Impossible to add new comment.")
        }
    }

}

enum class ServiceOrderStatus {
    OPEN, CLOSED, CANCELLED;
}

