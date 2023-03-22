package com.example.onlineshop.common.data.di

import com.example.onlineshop.common.data.api.ApiConstants.BASE_ENDPOINT
import com.example.onlineshop.common.data.api.ShopApi
import com.example.onlineshop.common.data.api.interceptors.LoggingInterceptor
import com.example.onlineshop.common.data.api.interceptors.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ShopApi {
        return builder.build().create(ShopApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}