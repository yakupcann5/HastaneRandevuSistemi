package com.example.hastanerandevusistemi.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.use_case.user.GetUserByTcAndPasswordUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    var getUserByTcAndPasswordUseCase: GetUserByTcAndPasswordUseCase
) : BaseViewModel(application) {

    var loginState : MutableLiveData<Boolean> = MutableLiveData()

    fun getUserByTcAndPassword(tc: Int, password: String) {
        getUserByTcAndPasswordUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getUserByTcAndPassword: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "getUserByTcAndPassword: ${it.data}")
                    loginState.value = true
                }

                is RequestState.Error -> {
                    Log.d("TAG", "getUserByTcAndPassword: ${it.message}")
                    loginState.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}