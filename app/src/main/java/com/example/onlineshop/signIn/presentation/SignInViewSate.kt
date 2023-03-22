package com.example.onlineshop.signIn.presentation

import com.example.onlineshop.common.presentation.Event

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
data class SignInViewSate(
    val isUserCreated: Boolean = false,
    val isEmailExist: Boolean = false,
    val isLoading: Boolean = false,
    val failure: Event<Throwable>? = null
)
