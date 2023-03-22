package com.example.onlineshop.common.presentation.model.mappers

import com.example.onlineshop.common.domain.model.Product
import com.example.onlineshop.common.presentation.model.UiLatestProduct
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class UiLatestProductMapper @Inject constructor() : UiMapper<Product, UiLatestProduct> {
    override fun mapToUi(input: Product): UiLatestProduct {
        return UiLatestProduct(
            category = input.category,
            imageUrl = input.imageUrl,
            price = input.price,
            name = input.name
        )
    }
}