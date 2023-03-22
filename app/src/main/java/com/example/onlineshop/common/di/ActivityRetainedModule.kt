package com.example.onlineshop.common.di

import com.example.onlineshop.common.data.OnlineShopRepository
import com.example.onlineshop.common.domain.repositories.ShopRepository
import com.example.onlineshop.common.utils.CoroutineDispatchersProvider
import com.example.onlineshop.common.utils.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindShopRepository(repository: OnlineShopRepository): ShopRepository

    @Binds
    abstract fun bindDispatchersProvide(
        dispatchersProvider: CoroutineDispatchersProvider
    ): DispatchersProvider

}