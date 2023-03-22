package com.example.onlineshop.common.data.cache

import com.example.onlineshop.common.data.cache.model.User

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface Cache {

    suspend fun getUser(firstName: String): User?

    suspend fun isEmailExist(email: String): Boolean

    suspend fun insertUser(user: User):Long

}