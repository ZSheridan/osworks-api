package com.algaworks.osworks.api.model

import com.algaworks.osworks.domain.model.ServiceOrder
import org.modelmapper.ModelMapper
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ServiceOrderInput (
        @get:Valid @get:NotNull
        var client: ClientIdInput? = null,
        @get:NotBlank
        var description: String? = null,
        @get:NotNull
        var price: BigDecimal? = null
) {

    private val modelMapper = ModelMapper()

    fun toServiceOrder(): ServiceOrder = modelMapper.map(this, ServiceOrder::class.java)

}