package com.example.whatsappclone.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappclone.presentation.repository.AuthRepository
import com.example.whatsappclone.states.SignInState
import com.example.whatsappclone.states.SignUpState
import com.example.whatsappclone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message!!))
                }
                is Resource.Loading ->
                {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Success ->
                {
                    _signUpState.send(SignUpState(isSuccess = "Is Success Register"))
                }
            }
        }
    }
}