package com.example.onlineshop.common.domain.model

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
data class Product(
 val category: String,
 val imageUrl: String,
 val name: String,
 val discount: Int,
 val price: Float
)
