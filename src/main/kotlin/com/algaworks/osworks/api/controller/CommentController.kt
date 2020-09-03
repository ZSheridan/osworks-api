package com.algaworks.osworks.api.controller

import com.algaworks.osworks.api.model.CommentInput
import com.algaworks.osworks.api.model.CommentModel
import com.algaworks.osworks.api.model.OutputModel
import com.algaworks.osworks.domain.service.CrudServiceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("service-orders/{serviceOrderId}/comments")
class CommentController(
        @Autowired
        private val crudServiceOrderService: CrudServiceOrderService
) {

    private val model = OutputModel()

    @GetMapping
    fun list(@PathVariable serviceOrderId: Long): List<CommentModel> {
        val comments = crudServiceOrderService.read(serviceOrderId).comments
        return model.mapCollection(comments)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable serviceOrderId: Long,
               @Valid @RequestBody commentInput: CommentInput): CommentModel {
        val comment = commentInput.toComment()
        return model.map(
                crudServiceOrderService.addComment(serviceOrderId, comment)
        )
    }

}