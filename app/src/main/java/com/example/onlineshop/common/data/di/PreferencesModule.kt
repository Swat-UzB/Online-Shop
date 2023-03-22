package com.example.onlineshop.common.data.di

import com.example.onlineshop.common.data.preferences.Preferences
import com.example.onlineshop.common.data.preferences.ShopPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun providePreferences(preferences: ShopPreferences): Preferences
}