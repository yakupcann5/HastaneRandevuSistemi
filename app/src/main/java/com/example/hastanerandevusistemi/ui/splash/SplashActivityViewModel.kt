package com.example.hastanerandevusistemi.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.City
import com.example.hastanerandevusistemi.domain.model.Data
import com.example.hastanerandevusistemi.domain.model.District
import com.example.hastanerandevusistemi.domain.model.Hastane
import com.example.hastanerandevusistemi.domain.use_case.city.GetCityItemCountUseCase
import com.example.hastanerandevusistemi.domain.use_case.city.SaveCityUseCase
import com.example.hastanerandevusistemi.domain.use_case.district.SaveDistrictUseCase
import com.example.hastanerandevusistemi.domain.use_case.hospital.SaveHospitalUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import com.example.hastanerandevusistemi.util.Helper
import com.example.hastanerandevusistemi.util.MyPreferences
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    application: Application,
    private val preferences: MyPreferences
) :
    BaseViewModel(application) {
    @Inject
    lateinit var getCityItemCountUseCase: GetCityItemCountUseCase

    @Inject
    lateinit var saveCityUseCase: SaveCityUseCase

    @Inject
    lateinit var saveDistrictUseCase: SaveDistrictUseCase

    @Inject
    lateinit var saveHospitalUseCase: SaveHospitalUseCase

    private var data: Data? = null
    private var city: ArrayList<City> = arrayListOf()
    private var district: ArrayList<District> = arrayListOf()
    private var hospital: ArrayList<Hastane> = arrayListOf()
    private var department: ArrayList<String> = arrayListOf()
    private var doctor: ArrayList<String> = arrayListOf()
    private var day: ArrayList<String> = arrayListOf()
    private var hour: ArrayList<String> = arrayListOf()

    val itemCityCount = MutableLiveData<Boolean>()

    fun firstOpen(): Boolean {
        if (!preferences.getBoolean("firstOpen")) {
            preferences.setBoolean("firstOpen", true)
            return true
        }
        return false
    }

    fun getCityItemCount() {
        getCityItemCountUseCase.invoke().onEach { result ->
            when (result) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getCityItemCount: ")
                }

                is RequestState.Success -> {
                    itemCityCount.value = true
                    Log.d("TAG", "getCityItemCount: Success")
                }

                is RequestState.Error -> {
                    Log.d("TAG", "getCityItemCount: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setCityData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                city.add(
                    City(
                        it.districts,
                        it.text,
                        it.value
                    )
                )
            }
            Log.d("TAG", "setCityData: ${data?.data?.get(0)?.text}")
            saveCityUseCase.invoke(city).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setCityData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setCityData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setCityData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDistrictData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    this.district.add(
                        District(
                            district.hastane,
                            district.ilId,
                            district.text,
                            district.value
                        )
                    )
                }
            }
            Log.d("TAG", "setDistrictData: ${data?.data?.get(0)?.text}")
            saveDistrictUseCase.invoke(district).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDistrictData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDistrictData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDistrictData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setHospitalData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hospital ->
                        this.hospital.add(
                            Hastane(
                                hospital.ilceId,
                                hospital.polikinlik,
                                hospital.text,
                                hospital.value
                            )
                        )
                    }
                }
            }
            Log.d("TAG", "setHospitalData: ${data?.data?.get(0)?.text}")
            saveHospitalUseCase.invoke(hospital).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setHospitalData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setHospitalData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setHospitalData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}