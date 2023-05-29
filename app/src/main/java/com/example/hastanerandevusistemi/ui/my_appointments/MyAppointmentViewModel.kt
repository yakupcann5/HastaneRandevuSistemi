package com.example.hastanerandevusistemi.ui.my_appointments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.data.local.entities.UserEntity
import com.example.hastanerandevusistemi.domain.model.Appointment
import com.example.hastanerandevusistemi.domain.use_case.appointment.GetAppointmentByUserUseCase
import com.example.hastanerandevusistemi.domain.use_case.user.GetUserByTcAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyAppointmentViewModel @Inject constructor(
    private var getAppointmentByUserUseCase: GetAppointmentByUserUseCase,
    private var getUserByTcAndPasswordUseCase: GetUserByTcAndPasswordUseCase
) : ViewModel() {

    var userId: MutableLiveData<RequestState<UserEntity?>> = MutableLiveData()
    var myAppointment: MutableLiveData<RequestState<List<AppointmentEntity>>?>? = MutableLiveData()

    fun getAppointmentByUser(userId: Int) {
        getAppointmentByUserUseCase.invoke(userId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getAppointmentByUser: Loading")
                }
                is RequestState.Success -> {
                    myAppointment?.value = it
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getAppointmentByUser: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUserId(tc: Long, password: String) {
        getUserByTcAndPasswordUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getUserId: Loading")
                }
                is RequestState.Success -> {
                    userId.value = RequestState.Success(it.data)
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getUserId: Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}