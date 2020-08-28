package com.algaworks.osworks.api.model

import org.modelmapper.ModelMapper

open class OutputModel {

    val modelMapper = ModelMapper()

    inline fun <reified T> map(source: Any): T {
        return modelMapper.map(source, T::class.java)
    }

    inline fun <reified T> mapCollection(source: List<Any>): List<T> {
        return source.map { item -> map(item) }
    }

}