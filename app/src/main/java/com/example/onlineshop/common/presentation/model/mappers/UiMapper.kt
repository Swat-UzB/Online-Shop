package com.example.onlineshop.common.presentation.model.mappers

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface UiMapper<E, U> {
    fun mapToUi(input: E): U
}