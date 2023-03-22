package com.example.onlineshop.common.data.api.model.mappers

import com.example.onlineshop.common.data.api.model.ApiProduct
import com.example.onlineshop.common.domain.model.Product
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class ApiFlashSaleMapper @Inject constructor() : ApiMapper<ApiProduct?, Product> {
    override fun mapToDomain(apiEntity: ApiProduct?): Product {
        return Product(
            category = apiEntity?.category.orEmpty(),
            imageUrl = apiEntity?.imageUrl.orEmpty(),
            name = apiEntity?.name.orEmpty(),
            discount = 0,
            price = apiEntity?.price ?: 0.0f
        )
    }
}