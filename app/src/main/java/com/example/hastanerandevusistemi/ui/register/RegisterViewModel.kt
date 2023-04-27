package com.example.hastanerandevusistemi.ui.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
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
    application: Application,
    private val saveUserUseCase: SaveUserUseCase
) : BaseViewModel(application) {

    var addUserModel: MutableLiveData<User> = MutableLiveData()
    var addUserModelIsFull: MutableLiveData<Boolean> = MutableLiveData()
    var addUserName: MutableLiveData<String?> = MutableLiveData()
    var addUserSurname: MutableLiveData<String> = MutableLiveData()
    var addUserTC: MutableLiveData<String> = MutableLiveData()
    var addUserEmail: MutableLiveData<String> = MutableLiveData()
    var addUserPassword: MutableLiveData<String> = MutableLiveData()

    fun checkInput() {
        addUserModelIsFull.value =
            !addUserName.value.isNullOrEmpty() &&
                    !addUserSurname.value.isNullOrEmpty() &&
                    !addUserTC.value.isNullOrEmpty() &&
                    !addUserEmail.value.isNullOrEmpty() &&
                    !addUserPassword.value.isNullOrEmpty()
    }

    fun addUser() {
        saveUserUseCase.invoke(
            User(
                0,
                addUserName.value.toString(),
                addUserSurname.value.toString(),
                addUserTC.value.toString().toInt(),
                addUserEmail.value.toString(),
                addUserPassword.value.toString()
            )
        ).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "addUser: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "addUser: ${it.data}")
                }

                is RequestState.Error -> {
                    Log.d("TAG", "addUser: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}