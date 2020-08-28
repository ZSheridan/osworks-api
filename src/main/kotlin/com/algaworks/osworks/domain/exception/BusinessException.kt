package com.algaworks.osworks.domain.exception

import java.lang.RuntimeException

open class BusinessException(message: String) : RuntimeException(message)