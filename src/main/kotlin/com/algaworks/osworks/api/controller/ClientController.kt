package com.algaworks.osworks.api.controller

import com.algaworks.osworks.api.model.ClientInput
import com.algaworks.osworks.api.model.ClientModel
import com.algaworks.osworks.api.model.OutputModel
import com.algaworks.osworks.domain.repository.ClientRepository
import com.algaworks.osworks.domain.service.CrudClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/clients")
class ClientController {

    @Autowired
    private val crudClientService = CrudClientService()

    @Autowired
    private val clientRepository: ClientRepository? = null

    private val model = OutputModel()

    @GetMapping
    fun list(): List<ClientModel>? {
        return clientRepository?.findAll()?.let { model.mapCollection(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody clientInput: ClientInput): ClientModel {
        return model.map(crudClientService.save(clientInput.toClient()))
    }

    @GetMapping("/{clientId}")
    fun readById(@PathVariable clientId: Long): ResponseEntity<ClientModel> {
        val client = crudClientService.read(clientId)
        return ResponseEntity.ok(model.map(client))
    }

    @PutMapping("/{clientId}")
    fun update(@PathVariable clientId: Long,
               @Valid @RequestBody clientInput: ClientInput): ResponseEntity<ClientModel> {
        crudClientService.read(clientId)
        val client = clientInput.toClient()
        client.id = clientId
        crudClientService.save(client)
        return ResponseEntity.ok(model.map(client))
    }

    @DeleteMapping("/{clientId}")
    fun delete(@PathVariable clientId: Long): ResponseEntity<Unit> {
        crudClientService.read(clientId)
        crudClientService.delete(clientId)
        return ResponseEntity.noContent().build()
    }

}