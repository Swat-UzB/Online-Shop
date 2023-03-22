package com.example.onlineshop.common.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    val firstName: String,
    val lastName:String,
    val email:String,
    val password:String = "1234"
)
