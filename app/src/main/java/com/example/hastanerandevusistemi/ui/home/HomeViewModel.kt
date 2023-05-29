package com.example.hastanerandevusistemi.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.hastanerandevusistemi.ui.BaseViewModel
import com.example.hastanerandevusistemi.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val myPreferences: MyPreferences
) : BaseViewModel(application) {
    var userId: MutableLiveData<Long> = MutableLiveData()
    var userPassword: MutableLiveData<String> = MutableLiveData()

    fun getUserInfo() {
        myPreferences.getLong("userId", 0).let {
            userId.value = it
        }
        myPreferences.getString("userPassword", "").let {
            userPassword.value = it
        }
    }
}