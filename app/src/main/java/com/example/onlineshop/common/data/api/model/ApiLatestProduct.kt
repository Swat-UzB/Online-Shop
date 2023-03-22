package com.example.onlineshop.common.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
data class ApiLatestProduct(
    @field: SerializedName("latest") val latest :List<ApiProduct>
)

data class ApiProduct(
    @field: SerializedName("category") val category: String?,
    @field: SerializedName("name") val name: String?,
    @field: SerializedName("price") val price: Float?,
    @field: SerializedName("image_url") val imageUrl: String?,
)
