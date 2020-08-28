package com.algaworks.osworks.domain.repository

import com.algaworks.osworks.domain.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long>