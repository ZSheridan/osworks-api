package com.algaworks.osworks.api.model

import org.modelmapper.ModelMapper

open class OutputModel {

    val modelMapper = ModelMapper()

    inline fun <reified T> mapFrom(source: Any): T {
        return modelMapper.map(source, T::class.java)
    }

    inline fun <reified T> mapCollectionFrom(source: List<Any>): List<T> {
        return source.map { item -> mapFrom(item) }
    }

}