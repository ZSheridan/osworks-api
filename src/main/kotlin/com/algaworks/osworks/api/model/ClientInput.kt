package com.algaworks.osworks.api.model

import com.algaworks.osworks.domain.model.Client
import org.modelmapper.ModelMapper
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class ClientInput(
        @get:NotBlank @get:Size(max = 60)
        var name: String? = null,

        @get:NotBlank @get:Email @get:Size(max = 255)
        var email: String? = null,

        @get:NotBlank @get:Size(max = 20)
        var telephone: String? = null
) {

    private val modelMapper = ModelMapper()

    fun toClient(): Client = modelMapper.map(this, Client::class.java)

}