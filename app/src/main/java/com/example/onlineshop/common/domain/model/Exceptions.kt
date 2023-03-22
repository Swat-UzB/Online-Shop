package com.example.onlineshop.common.domain.model

import java.io.IOException

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String): Exception(message)

