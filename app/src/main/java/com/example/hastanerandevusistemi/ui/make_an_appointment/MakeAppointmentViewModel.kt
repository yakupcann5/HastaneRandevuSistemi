package com.example.hastanerandevusistemi.ui.make_an_appointment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.*
import com.example.hastanerandevusistemi.domain.model.Appointment
import com.example.hastanerandevusistemi.domain.use_case.appointment.SaveAppointmentUseCase
import com.example.hastanerandevusistemi.domain.use_case.city.GetAllCityUseCase
import com.example.hastanerandevusistemi.domain.use_case.days.GetAllDayUseCase
import com.example.hastanerandevusistemi.domain.use_case.district.GetAllDistrictUseCase
import com.example.hastanerandevusistemi.domain.use_case.doctor.GetAllDoctorUseCase
import com.example.hastanerandevusistemi.domain.use_case.hospital.GetHospitalUseCase
import com.example.hastanerandevusistemi.domain.use_case.hour.ChangeHourValueUseCase
import com.example.hastanerandevusistemi.domain.use_case.hour.GetAllHourUseCase
import com.example.hastanerandevusistemi.domain.use_case.polyclinic.GetPoliklinikUseCase
import com.example.hastanerandevusistemi.domain.use_case.user.GetUserByTcAndPasswordUseCase
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
    private var getHourUseCase: GetAllHourUseCase,
    private var saveAppointmentUseCase: SaveAppointmentUseCase,
    private var getUserByTcAndPasswordUseCase: GetUserByTcAndPasswordUseCase,
    private var changeHourValueUseCase: ChangeHourValueUseCase
) : BaseViewModel(application) {
    var city: MutableLiveData<List<CityEntity>?> = MutableLiveData()
    var district: MutableLiveData<List<DistrictEntity>?> = MutableLiveData()
    var hospital: MutableLiveData<List<HospitalEntity>?> = MutableLiveData()
    var depertmant: MutableLiveData<List<PolyclinicEntity>?> = MutableLiveData()
    var doctor: MutableLiveData<List<DoctorEntity>?> = MutableLiveData()
    var date: MutableLiveData<List<DaysEntity>?> = MutableLiveData()
    var hour: MutableLiveData<List<HourEntity>?> = MutableLiveData()
    var appointmentSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var userId: MutableLiveData<Int?> = MutableLiveData()

    var selectedCityId: MutableLiveData<Int> = MutableLiveData()
    var selectedCityName: MutableLiveData<String> = MutableLiveData()
    var selectedDistrictId: MutableLiveData<Int> = MutableLiveData()
    var selectedDistrictName: MutableLiveData<String> = MutableLiveData()
    var selectedHospitalId: MutableLiveData<Int> = MutableLiveData()
    var selectedHospitalName: MutableLiveData<String> = MutableLiveData()
    var selectedDepertmantId: MutableLiveData<Int> = MutableLiveData()
    var selectedDepertmantName: MutableLiveData<String> = MutableLiveData()
    var selectedDoctorId: MutableLiveData<Int> = MutableLiveData()
    var selectedDoctorName: MutableLiveData<String> = MutableLiveData()
    var selectedDateId: MutableLiveData<Int> = MutableLiveData()
    var selectedDateName: MutableLiveData<String> = MutableLiveData()
    var selectedHourId: MutableLiveData<Int> = MutableLiveData()
    var selectedHourName: MutableLiveData<String> = MutableLiveData()

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

    fun observeAppointmentInfo() {

    }

    fun randevuAl(
        userId: Int,
        cityId: Int,
        cityName: String,
        hospitalId: Int,
        hospitalName: String,
        departmentId: Int,
        departmentName: String,
        doctorId: Int,
        doctorName: String,
        dateId: Int,
        dateName: String,
        hourId: Int,
        hourName: String
    ) {
        val randevu: ArrayList<Appointment> = arrayListOf()
        randevu.add(
            Appointment(
                userId,
                cityId,
                cityName,
                hospitalId,
                hospitalName,
                departmentId,
                departmentName,
                doctorId,
                doctorName,
                dateId,
                dateName,
                hourId,
                hourName
            )
        )
        saveAppointmentUseCase.invoke(randevu).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "randevuAl: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "randevuAl: Success")
                    changeHourValueUseCase.invoke(randevu[0]).onEach { result ->
                        when (result) {
                            is RequestState.Loading -> {
                                Log.d("TAG", "randevuKaydet: Loading")
                            }

                            is RequestState.Success -> {
                                Log.d("TAG", "randevuKaydet: Success")
                                appointmentSuccess.value = true
                            }

                            is RequestState.Error -> {
                                Log.d("TAG", "randevuKaydet: Error")
                            }
                        }
                    }.launchIn(viewModelScope)
                }
                is RequestState.Error -> {
                    Log.d("TAG", "randevuAl: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUserInfo(int: Long, string: String) {
        getUserByTcAndPasswordUseCase.invoke(int, string).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    userId.value = it.data?.id
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
