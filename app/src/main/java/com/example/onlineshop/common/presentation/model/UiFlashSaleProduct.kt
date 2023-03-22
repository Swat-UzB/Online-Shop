package com.example.onlineshop.common.presentation.model

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
data class UiFlashSaleProduct(
    val category: String,
    val imageUrl: String,
    val name: String,
    val discount: Int,
    val price: Float
)
