package com.algaworks.osworks.domain.service

import com.algaworks.osworks.domain.exception.BusinessException
import com.algaworks.osworks.domain.exception.EntityNotFoundException
import com.algaworks.osworks.domain.model.Client
import com.algaworks.osworks.domain.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CrudClientService {

    @Autowired
    private val clientRepository: ClientRepository? = null

    fun save(client: Client): Client {
        clientRepository?.let {
            val existingClient = it.findByEmail(client.email!!)
            if (existingClient != null && existingClient != client)
                throw BusinessException("Email address already in use.")
        }
        return clientRepository?.save(client)!!
    }

    fun read(clientId: Long): Client {
        return clientRepository?.findByIdOrNull(clientId)
                ?: throw EntityNotFoundException ("Client not found.")
    }

    fun delete(clientId: Long) {
        clientRepository?.deleteById(clientId)
    }

}