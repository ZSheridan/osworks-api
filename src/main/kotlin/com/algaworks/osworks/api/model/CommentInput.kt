package com.algaworks.osworks.api.model

import com.algaworks.osworks.domain.model.Comment
import org.modelmapper.ModelMapper
import javax.validation.constraints.NotBlank

class CommentInput (@get:NotBlank var description: String? = null) {

    private val modelMapper = ModelMapper()

    fun toComment(): Comment = modelMapper.map(this, Comment::class.java)

}