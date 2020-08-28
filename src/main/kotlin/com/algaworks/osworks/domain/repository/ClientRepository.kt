package com.algaworks.osworks.domain.repository

import com.algaworks.osworks.domain.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {
    fun findByName(name: String): List<Client>?
    fun findByEmail(email: String): Client?
}