package com.algaworks.osworks.api.model

import javax.validation.constraints.NotNull

//ClientId model for ServiceOrderInput client
class ClientIdInput (@get:NotNull val id: Long? = null)