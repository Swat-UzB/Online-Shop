package com.example.onlineshop.common.data

import com.example.onlineshop.common.data.api.ShopApi
import com.example.onlineshop.common.data.api.model.mappers.ApiFlashSaleMapper
import com.example.onlineshop.common.data.api.model.mappers.ApiProductMapper
import com.example.onlineshop.common.data.cache.Cache
import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.domain.model.NetworkException
import com.example.onlineshop.common.domain.model.Product
import com.example.onlineshop.common.domain.repositories.ShopRepository
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class OnlineShopRepository @Inject constructor(
    private val api: ShopApi,
    private val cache: Cache,
    private val apiProductMapper: ApiProductMapper,
    private val apiFlashSaleMapper: ApiFlashSaleMapper
) : ShopRepository {
    override suspend fun getUser(firstName: String): User? {
        return cache.getUser(firstName)
    }

    override suspend fun isEmailExist(email: String): Boolean {
        return cache.isEmailExist(email)
    }

    override suspend fun insertUser(newUser: User): Long {
        return cache.insertUser(user = newUser)
    }

    override suspend fun getLatestProducts(): List<Product> {
        try {
            return api.getLatestProducts().latest.map { apiFlashSaleMapper.mapToDomain(it) }
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun getFlashSaleProducts(): List<Product> {
        try {
            return api.getFlashSaleProducts().flashSale.map { apiProductMapper.mapToDomain(it) }
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }
}