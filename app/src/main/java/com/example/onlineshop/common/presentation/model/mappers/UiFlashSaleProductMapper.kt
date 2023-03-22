package com.example.onlineshop.common.presentation.model.mappers

import com.example.onlineshop.common.domain.model.Product
import com.example.onlineshop.common.presentation.model.UiFlashSaleProduct
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class UiFlashSaleProductMapper @Inject constructor() : UiMapper<Product, UiFlashSaleProduct> {
    override fun mapToUi(input: Product): UiFlashSaleProduct {
        return UiFlashSaleProduct(
            category = input.category,
            imageUrl = input.imageUrl,
            name = input.name,
            discount = input.discount,
            price = input.price
        )
    }
}