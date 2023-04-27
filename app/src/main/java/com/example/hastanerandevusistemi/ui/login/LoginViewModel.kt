package com.example.hastanerandevusistemi.ui.login

import android.app.Application
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
}