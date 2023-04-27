package com.example.hastanerandevusistemi.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.City
import com.example.hastanerandevusistemi.domain.model.Data
import com.example.hastanerandevusistemi.domain.model.District
import com.example.hastanerandevusistemi.domain.model.Doktor
import com.example.hastanerandevusistemi.domain.model.Gunler
import com.example.hastanerandevusistemi.domain.model.Hastane
import com.example.hastanerandevusistemi.domain.model.Polikinlik
import com.example.hastanerandevusistemi.domain.model.Saatler
import com.example.hastanerandevusistemi.domain.use_case.doctor.SaveDoctorUseCase
import com.example.hastanerandevusistemi.domain.use_case.city.GetCityItemCountUseCase
import com.example.hastanerandevusistemi.domain.use_case.city.SaveCityUseCase
import com.example.hastanerandevusistemi.domain.use_case.days.SaveDaysUseCase
import com.example.hastanerandevusistemi.domain.use_case.district.SaveDistrictUseCase
import com.example.hastanerandevusistemi.domain.use_case.hospital.SaveHospitalUseCase
import com.example.hastanerandevusistemi.domain.use_case.hour.SaveHourUseCase
import com.example.hastanerandevusistemi.domain.use_case.polyclinic.SavePolyclinicUseCase
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

    @Inject
    lateinit var savePolyclinicUseCase: SavePolyclinicUseCase

    @Inject
    lateinit var saveDoctorUseCase: SaveDoctorUseCase

    @Inject
    lateinit var saveDaysUseCase: SaveDaysUseCase

    @Inject
    lateinit var saveHourUseCase: SaveHourUseCase

    private var data: Data? = null
    private var city: ArrayList<City> = arrayListOf()
    private var district: ArrayList<District> = arrayListOf()
    private var hospital: ArrayList<Hastane> = arrayListOf()
    private var polyclinic: ArrayList<Polikinlik> = arrayListOf()
    private var doctor: ArrayList<Doktor> = arrayListOf()
    private var day: ArrayList<Gunler> = arrayListOf()
    private var hour: ArrayList<Saatler> = arrayListOf()

    fun firstOpen(): Boolean {
        if (!preferences.getBoolean("firstOpen")) {
            preferences.setBoolean("firstOpen", true)
            return true
        }
        return false
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

    fun setPolyclinicData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hospital ->
                        hospital.polikinlik.forEach { polyclinic ->
                            this.polyclinic.add(
                                Polikinlik(
                                    polyclinic.doktor,
                                    polyclinic.hastaneId,
                                    polyclinic.text,
                                    polyclinic.value
                                )
                            )
                        }
                    }
                }
            }
            Log.d("TAG", "setPolyclinicData: ${data?.data?.get(0)?.text}")
            savePolyclinicUseCase.invoke(polyclinic).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setPolyclinicData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setPolyclinicData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setPolyclinicData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDoctorData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hospital ->
                        hospital.polikinlik.forEach { polyclinic ->
                            polyclinic.doktor.forEach { doctor ->
                                this.doctor.add(
                                    Doktor(
                                        doctor.gunler,
                                        doctor.poliklinikId,
                                        doctor.name,
                                        doctor.text,
                                        doctor.value
                                    )
                                )
                            }
                        }
                    }
                }
            }
            Log.d("TAG", "setDoctorData: ${data?.data?.get(0)?.text}")
            saveDoctorUseCase.invoke(doctor).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDoctorData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDoctorData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDoctorData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDaysData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hospital ->
                        hospital.polikinlik.forEach { polyclinic ->
                            polyclinic.doktor.forEach { doctor ->
                                doctor.gunler.forEach { day ->
                                    this.day.add(
                                        Gunler(
                                            day.doktorId,
                                            day.saatler,
                                            day.text,
                                            day.value
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            saveDaysUseCase.invoke(day).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDaysData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDaysData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDaysData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setHourData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(getApplication(), "il.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hospital ->
                        hospital.polikinlik.forEach { polyclinic ->
                            polyclinic.doktor.forEach { doctor ->
                                doctor.gunler.forEach { day ->
                                    day.saatler.forEach { hour ->
                                        this.hour.add(
                                            Saatler(
                                                hour.gunId,
                                                hour.selected,
                                                hour.text,
                                                hour.value
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Log.d("TAG", "setHourData: ${data?.data?.get(0)?.text}")
            saveHourUseCase.invoke(hour).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setHourData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setHourData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setHourData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}