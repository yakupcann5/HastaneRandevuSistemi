package com.example.hastanerandevusistemi.ui.make_an_appointment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.*
import com.example.hastanerandevusistemi.domain.use_case.city.GetAllCityUseCase
import com.example.hastanerandevusistemi.domain.use_case.days.GetAllDayUseCase
import com.example.hastanerandevusistemi.domain.use_case.district.GetAllDistrictUseCase
import com.example.hastanerandevusistemi.domain.use_case.doctor.GetAllDoctorUseCase
import com.example.hastanerandevusistemi.domain.use_case.hospital.GetHospitalUseCase
import com.example.hastanerandevusistemi.domain.use_case.hour.GetAllHourUseCase
import com.example.hastanerandevusistemi.domain.use_case.polyclinic.GetPoliklinikUseCase
import com.example.hastanerandevusistemi.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MakeAppointmentViewModel
@Inject constructor(
    application: Application,
    private var getAllCityUseCase: GetAllCityUseCase,
    private var getAllDistrictUseCase: GetAllDistrictUseCase,
    private var getHospitalUseCase: GetHospitalUseCase,
    private var getPoliklinikUseCase: GetPoliklinikUseCase,
    private var getDoctorUseCase: GetAllDoctorUseCase,
    private var getDaysUseCase: GetAllDayUseCase,
    private var getHourUseCase: GetAllHourUseCase
) : BaseViewModel(application) {
    var city: MutableLiveData<List<CityEntity>?> = MutableLiveData()
    var district: MutableLiveData<List<DistrictEntity>?> = MutableLiveData()
    var hospital: MutableLiveData<List<HospitalEntity>?> = MutableLiveData()
    var depertmant: MutableLiveData<List<PolyclinicEntity>?> = MutableLiveData()
    var doctor: MutableLiveData<List<DoctorEntity>?> = MutableLiveData()
    var date: MutableLiveData<List<DaysEntity>?> = MutableLiveData()
    var hour: MutableLiveData<List<HourEntity>?> = MutableLiveData()

    fun getCityData() {
        getAllCityUseCase.invoke().onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    city.value = it.data
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDistrictData(ilId: Int) {
        getAllDistrictUseCase.invoke(ilId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    district.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun randevuAl() {

    }

    fun getHospitalData(discId: Int) {
        getHospitalUseCase.invoke(discId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    hospital.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDepartmentData(hospitalId: Int) {
        getPoliklinikUseCase.invoke(hospitalId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    depertmant.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDoctorData(departmentId: Int) {
        getDoctorUseCase.invoke(departmentId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    doctor.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDayData(doctorId: Int) {
        getDaysUseCase.invoke(doctorId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    date.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getHourData(dayId: Int) {
        getHourUseCase.invoke(dayId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    hour.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
