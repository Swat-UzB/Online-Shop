package com.example.onlineshop.common.data.api.model.mappers

import com.example.onlineshop.common.data.api.model.ApiFlashProduct
import com.example.onlineshop.common.domain.model.Product
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class ApiProductMapper @Inject constructor() : ApiMapper<ApiFlashProduct?, Product> {
    override fun mapToDomain(apiEntity: ApiFlashProduct?): Product {
        return Product(
            category = apiEntity?.category.orEmpty(),
            imageUrl = apiEntity?.imageUrl.orEmpty(),
            name = apiEntity?.name.orEmpty(),
            discount = apiEntity?.discount ?: 0,
            price = apiEntity?.price ?: 0.0f
        )
    }
}