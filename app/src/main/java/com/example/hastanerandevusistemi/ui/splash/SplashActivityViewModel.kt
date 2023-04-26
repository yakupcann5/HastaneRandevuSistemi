package com.example.hastanerandevusistemi.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.City
import com.example.hastanerandevusistemi.domain.model.Data
import com.example.hastanerandevusistemi.domain.use_case.SaveCityUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import com.example.hastanerandevusistemi.util.Helper
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.Collator
import java.util.Collections
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    application: Application,
) :
    BaseViewModel(application) {
    @Inject
    lateinit var saveCityUseCase: SaveCityUseCase

    private var data: Data? = null
    private var city: ArrayList<City> = arrayListOf()
    private var district: ArrayList<String> = arrayListOf()
    private var hospital: ArrayList<String> = arrayListOf()
    private var department: ArrayList<String> = arrayListOf()
    private var doctor: ArrayList<String> = arrayListOf()
    private var day: ArrayList<String> = arrayListOf()
    private var hour: ArrayList<String> = arrayListOf()

    fun setCityData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                city.add(City(it.districts, it.text, it.value))
            }
            Log.d("TAG", "setCityData: ${data?.data?.get(0)?.text}")
            saveCityUseCase.invoke(city).onEach {
                when (it) {
                    is RequestState.Loading -> { Log.d("TAG", "setCityData: ") }
                    is RequestState.Success -> { Log.d("TAG", "setCityData: Success") }
                    is RequestState.Error -> { Log.d("TAG", "setCityData: Error") }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}