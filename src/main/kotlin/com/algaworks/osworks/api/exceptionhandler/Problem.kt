package com.algaworks.osworks.api.exceptionhandler

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.time.OffsetDateTime

class Field (val parameterName: String,
             val message: String?)

@JsonInclude(Include.NON_NULL)
class Problem (val status: Int? = null,
               val title: String? = null,
               val dateTime: OffsetDateTime? = null,
               val fields: List<Field>? = null)