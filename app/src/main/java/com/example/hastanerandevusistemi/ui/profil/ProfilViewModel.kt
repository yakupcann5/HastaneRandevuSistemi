package com.example.hastanerandevusistemi.ui.profil

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.UserEntity
import com.example.hastanerandevusistemi.domain.use_case.user.GetUserByTcAndPasswordUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    application: Application,
    private val getUserByTcAndPasswordUseCase: GetUserByTcAndPasswordUseCase
) : BaseViewModel(application) {
    var userInfo: MutableLiveData<RequestState<UserEntity?>> = MutableLiveData()

    fun getUserInfo(tc: Int, password: String) {
        getUserByTcAndPasswordUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {
                    userInfo.value = RequestState.Success(it.data)
                }
                is RequestState.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}