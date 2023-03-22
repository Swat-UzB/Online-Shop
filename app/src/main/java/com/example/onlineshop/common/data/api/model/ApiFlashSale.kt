package com.example.onlineshop.common.data.api.model

import com.google.gson.annotations.SerializedName

data class ApiFlashSale(
    @field: SerializedName("flash_sale")  val flashSale: List<ApiFlashProduct>
)

data class ApiFlashProduct(
    @field: SerializedName("category") val category: String?,
    @field: SerializedName("name") val name: String?,
    @field: SerializedName("price") val price: Float?,
    @field: SerializedName("discount") val discount: Int?,
    @field: SerializedName("image_url") val imageUrl: String?,
)