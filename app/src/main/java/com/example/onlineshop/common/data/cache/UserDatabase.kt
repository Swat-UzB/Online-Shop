package com.example.onlineshop.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineshop.common.data.cache.daos.UsersDao
import com.example.onlineshop.common.data.cache.model.User

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}