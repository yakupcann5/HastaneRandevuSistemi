package com.example.hastanerandevusistemi.ui.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.User
import com.example.hastanerandevusistemi.domain.use_case.user.SaveUserUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    val registerState : MutableLiveData<Boolean> = MutableLiveData()

    fun addUser(
        userName: String,
        userSurname: String,
        userTC: String,
        userEmail: String,
        userPassword: String
    ) {
        saveUserUseCase.invoke(
            User(
                0,
                userName,
                userSurname,
                userTC.toInt(),
                userEmail,
                userPassword
            )
        ).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "addUser: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "addUser: ${it.data}")
                    registerState.value = true
                }

                is RequestState.Error -> {
                    Log.d("TAG", "addUser: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}