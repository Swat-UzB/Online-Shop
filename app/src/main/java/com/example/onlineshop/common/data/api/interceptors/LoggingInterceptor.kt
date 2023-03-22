package com.example.onlineshop.common.data.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class LoggingInterceptor @Inject constructor() :HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        Timber.i(message)
    }

}