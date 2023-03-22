package com.example.onlineshop.common.data.cache

import com.example.onlineshop.common.data.cache.daos.UsersDao
import com.example.onlineshop.common.data.cache.model.User
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class RoomCache @Inject constructor(
    private val usersDao: UsersDao
) : Cache {
    override suspend fun getUser(firstName: String): User? {
        return usersDao.getUser(firstName)
    }

    override suspend fun isEmailExist(email: String): Boolean {
        return usersDao.isEmailExist(email)
    }

    override suspend fun insertUser(user: User):Long {
       return usersDao.insert(user)
    }

}