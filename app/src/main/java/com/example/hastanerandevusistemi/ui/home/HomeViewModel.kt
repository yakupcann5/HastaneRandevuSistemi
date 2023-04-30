package com.example.hastanerandevusistemi.ui.home

import android.app.Application
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
) : BaseViewModel(application) {
}