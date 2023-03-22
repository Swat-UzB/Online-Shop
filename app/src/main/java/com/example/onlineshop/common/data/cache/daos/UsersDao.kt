package com.example.onlineshop.common.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshop.common.data.cache.model.User

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User):Long

    @Query("SELECT EXISTS(SELECT * FROM users WHERE email=:email )")
    suspend fun isEmailExist(email: String): Boolean

    @Query("SELECT * FROM users WHERE firstName=:firstName")
    suspend fun getUser(firstName: String): User?
}