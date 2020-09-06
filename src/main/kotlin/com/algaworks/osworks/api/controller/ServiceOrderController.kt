package com.algaworks.osworks.api.controller

import com.algaworks.osworks.api.model.OutputModel
import com.algaworks.osworks.api.model.ServiceOrderInput
import com.algaworks.osworks.api.model.ServiceOrderModel
import com.algaworks.osworks.domain.repository.ServiceOrderRepository
import com.algaworks.osworks.domain.service.CrudServiceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/service-orders")
class ServiceOrderController (
        @Autowired
        private val crudServiceOrderService: CrudServiceOrderService,
        @Autowired
        private val serviceOrderRepository: ServiceOrderRepository,
)
{
    private val serviceOrderOutputModel = OutputModel()

    @GetMapping
    fun list(): List<ServiceOrderModel> {
        return serviceOrderOutputModel.mapCollectionFrom(
                serviceOrderRepository.findAll()
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody serviceOrderInput: ServiceOrderInput): ServiceOrderModel {
        return serviceOrderOutputModel.mapFrom(
                crudServiceOrderService.save(serviceOrderInput.toServiceOrder())
        )
    }

    @GetMapping("/{serviceOrderId}")
    fun readById(@PathVariable serviceOrderId: Long): ResponseEntity<ServiceOrderModel> {
        return ResponseEntity.ok(
                serviceOrderOutputModel.mapFrom(crudServiceOrderService.read(serviceOrderId))
        )
    }

    @PutMapping("/{serviceOrderId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun close(@PathVariable serviceOrderId: Long) {
        crudServiceOrderService.close(serviceOrderId)
    }

    @PutMapping("/{serviceOrderId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancel(@PathVariable serviceOrderId: Long) {
        crudServiceOrderService.cancel(serviceOrderId)
    }

}