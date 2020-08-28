package com.algaworks.osworks.domain.service

import com.algaworks.osworks.domain.exception.BusinessException
import com.algaworks.osworks.domain.exception.EntityNotFoundException
import com.algaworks.osworks.domain.model.Comment
import com.algaworks.osworks.domain.model.ServiceOrder
import com.algaworks.osworks.domain.model.ServiceOrderStatus
import com.algaworks.osworks.domain.repository.ClientRepository
import com.algaworks.osworks.domain.repository.CommentRepository
import com.algaworks.osworks.domain.repository.ServiceOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class CrudServiceOrderService {

    @Autowired
    private val serviceOrderRepository: ServiceOrderRepository? = null

    @Autowired
    private val clientRepository: ClientRepository? = null

    @Autowired
    private val commentRepository: CommentRepository? = null

    fun save(serviceOrder: ServiceOrder): ServiceOrder {
        serviceOrder.client = serviceOrder.client?.id?.let{
            clientRepository?.findByIdOrNull(it)
                    ?: throw BusinessException("Client not found.")
        }
        serviceOrder.status = ServiceOrderStatus.OPEN
        serviceOrder.openingDate = OffsetDateTime.now()
        return serviceOrderRepository?.save(serviceOrder)!!
    }

    fun read(serviceOrderId: Long): ServiceOrder {
        return serviceOrderRepository?.findByIdOrNull(serviceOrderId)
                ?: throw EntityNotFoundException("Service Order not found.")
    }

    fun addComment(serviceOrderId: Long, comment: Comment): Comment {
        comment.serviceOrder = read(serviceOrderId)
        comment.serviceOrder!!.canAddComment()
        comment.sendDate = OffsetDateTime.now()
        return commentRepository?.save(comment)!!
    }

    fun close(serviceOrderId: Long) {
        val serviceOrder = read(serviceOrderId)
        serviceOrder.close()
        serviceOrderRepository?.save(serviceOrder)
    }

    fun cancel(serviceOrderId: Long) {
        val serviceOrder = read(serviceOrderId)
        serviceOrder.cancel()
        serviceOrderRepository?.save(serviceOrder)
    }

}