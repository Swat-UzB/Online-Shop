package com.example.onlineshop.common.data.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.common.data.cache.Cache
import com.example.onlineshop.common.data.cache.RoomCache
import com.example.onlineshop.common.data.cache.UserDatabase
import com.example.onlineshop.common.data.cache.daos.UsersDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
            return Room.databaseBuilder(context, UserDatabase::class.java, "shop.db").build()
        }

        @Provides
        fun provideUsersDao(userDatabase: UserDatabase): UsersDao = userDatabase.usersDao()
    }
}