package com.algaworks.osworks.domain.repository

import com.algaworks.osworks.domain.model.ServiceOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceOrderRepository : JpaRepository<ServiceOrder, Long>