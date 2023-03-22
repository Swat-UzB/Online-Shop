package com.example.onlineshop.common.data.api.interceptors

import com.example.onlineshop.common.data.api.ConnectionManager
import com.example.onlineshop.common.domain.model.NetworkUnavailableException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        }else{
            throw NetworkUnavailableException()
        }
    }
}