package com.example.onlineshop.common.data.preferences

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
interface Preferences {

        fun putUserName(userName: String)

        fun getUserName(): String

        fun deleteUserName()
    }