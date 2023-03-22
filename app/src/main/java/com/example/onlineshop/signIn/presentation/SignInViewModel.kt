package com.example.onlineshop.signIn.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.data.preferences.Preferences
import com.example.onlineshop.common.presentation.Event
import com.example.onlineshop.common.utils.createExceptionHandler
import com.example.onlineshop.signIn.domain.CreateNewUser
import com.example.onlineshop.signIn.domain.IsEmailExist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val preferences: Preferences,
    private val checkIsEmailExist: IsEmailExist,
    private val createNewUser: CreateNewUser
) : ViewModel() {
    private val _state = MutableStateFlow(SignInViewSate())
    val state: StateFlow<SignInViewSate> get() = _state.asStateFlow()

init {
    Log.d("TTT","init SingInVM")
}
    private fun createUser(user: User) {
        val exceptionHandler = createExceptionHandler(
            message = "Failed to create user"
        )
        viewModelScope.launch(exceptionHandler) {
            createNewUser(user)
            preferences.putUserName(user.firstName + " " + user.lastName)
            _state.update { oldState ->
                oldState.copy(isUserCreated = true)
            }
        }
    }

    fun disableEmailExistError() {
        _state.update { oldState -> oldState.copy(isEmailExist = false) }
    }

    fun isEmailExist(user: User) {
        val exceptionHandler = createExceptionHandler(
            message = "Failed to check email"
        )
        viewModelScope.launch(exceptionHandler) {
            val isEmailExist = checkIsEmailExist(user.email)
            _state.update { oldState ->
                oldState.copy(isEmailExist = isEmailExist)
            }
            if (!isEmailExist) {
                createUser(user)
            }
        }
    }

    private fun createExceptionHandler(message: String): CoroutineExceptionHandler {
        return viewModelScope.createExceptionHandler(message) {
            onFailure(it)
        }
    }

    private fun onFailure(throwable: Throwable) {
        _state.update { oldState ->
            oldState.copy(failure = Event(throwable))
        }
    }

}