package com.example.onlineshop.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.data.preferences.Preferences
import com.example.onlineshop.common.presentation.Event
import com.example.onlineshop.common.utils.createExceptionHandler
import com.example.onlineshop.login.domain.GetUserData
import dagger.hilt.android.lifecycle.HiltViewModel
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
class LoginViewModel @Inject constructor(
    private val preferences: Preferences,
    private val getUserData: GetUserData
) : ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state: StateFlow<LoginViewState> get() = _state.asStateFlow()
    var user: User? = null
        private set

    fun isUserExist(firstName: String) {
        val errorMessage = "Failed to check user"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) {
            onFailure(it)
        }
        viewModelScope.launch(exceptionHandler) {
            user = getUserData(firstName)
            if (user != null) {
                preferences.putUserName(user?.firstName + " " + user?.lastName)
                _state.update { oldState ->
                    oldState.copy(isUserExist = true)
                }
            } else {
                _state.update { oldState ->
                    oldState.copy(showErrorUserNotExist = true)
                }
            }
        }
    }

    fun removeUserName() = preferences.deleteUserName()

    fun hideError() {
        _state.update { oldState ->
            oldState.copy(showErrorUserNotExist = false)
        }
    }

    private fun onFailure(failure: Throwable) {
        _state.update { oldState ->
            oldState.copy(
                failure = Event(failure)
            )

        }
    }
}