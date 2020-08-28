package com.algaworks.osworks.api.model

import com.algaworks.osworks.domain.model.ServiceOrderStatus
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.OffsetDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class ServiceOrderModel {
    var id: Long? = null
    var client: ClientInformationModel? = null
    var description: String? = null
    var price: BigDecimal? = null
    var serviceOrderStatus: ServiceOrderStatus? = null
    var openingDate: OffsetDateTime? = null
    var finishingDate: OffsetDateTime? = null
}