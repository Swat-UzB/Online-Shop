package com.example.onlineshop.login.presentation

import com.example.onlineshop.common.presentation.Event


/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

data class LoginViewState(
    val isUserExist: Boolean = false,
    val showErrorUserNotExist: Boolean = false,
    val isLoading: Boolean = false,
    val failure: Event<Throwable>? = null
)