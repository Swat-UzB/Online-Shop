package com.example.onlineshop.common.data.api.model.mappers

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}