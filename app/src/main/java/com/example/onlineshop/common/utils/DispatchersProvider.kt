package com.example.onlineshop.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface DispatchersProvider { fun io(): CoroutineDispatcher = Dispatchers.IO }