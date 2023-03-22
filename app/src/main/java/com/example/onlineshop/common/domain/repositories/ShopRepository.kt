package com.example.onlineshop.common.domain.repositories

import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.domain.model.Product

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface ShopRepository {
    suspend fun getUser(firstName: String): User?
    suspend fun isEmailExist(email:String):Boolean
    suspend fun insertUser(newUser: User):Long
    suspend fun getLatestProducts():List<Product>
    suspend fun getFlashSaleProducts():List<Product>
}