package com.example.onlineshop.common.data.api

import com.example.onlineshop.common.data.api.ApiConstants.FLASH_SALE_ENDPOINT
import com.example.onlineshop.common.data.api.ApiConstants.LATEST_ENDPOINT
import com.example.onlineshop.common.data.api.model.ApiFlashSale
import com.example.onlineshop.common.data.api.model.ApiLatestProduct
import retrofit2.http.GET

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface ShopApi {

    @GET(LATEST_ENDPOINT)
    suspend fun getLatestProducts(): ApiLatestProduct

    @GET(FLASH_SALE_ENDPOINT)
    suspend fun getFlashSaleProducts(): ApiFlashSale
}