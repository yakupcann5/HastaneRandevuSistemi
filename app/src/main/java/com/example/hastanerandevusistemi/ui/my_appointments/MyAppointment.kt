package com.example.hastanerandevusistemi.ui.my_appointments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.databinding.FragmentMyAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAppointment : Fragment() {
    private lateinit var binding: FragmentMyAppointmentBinding
    private val myAppointmentViewModel: MyAppointmentViewModel by viewModels()
    private lateinit var adapter: MyAppointmentRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAppointmentBinding.inflate(layoutInflater)
        initView()
        initRecyclerView()
        return binding.root
    }

    private fun getMyAppointment() {
        val appointmentList = ArrayList<AppointmentEntity>()
        myAppointmentViewModel.userId.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    it.data?.id?.let { it1 ->
                        myAppointmentViewModel.getAppointmentByUser(
                            it1
                        )
                    }
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getMyAppointment: error")
                }
                is RequestState.Loading -> {
                    Log.d("TAG", "getMyAppointment: Loading")
                }
            }
        }
        myAppointmentViewModel.myAppointment?.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    it.data?.let { it1 -> appointmentList.addAll(it1) }
                    adapter.submitList(appointmentList)
                }
                is RequestState.Loading -> {
                    Log.d("TAG", "getMyAppointment: Loading")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getMyAppointment: Error")
                }
                else -> {}
            }
        }
    }

    private fun initRecyclerView() {
        adapter = MyAppointmentRecyclerAdapter()
        binding.myAppointmentRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.myAppointmentRecyclerview.adapter = adapter
    }

    private fun initView() {
        val userTc = arguments?.getLong("tc")
        val userPassword = arguments?.getString("password")
        if (userTc != null) {
            if (userPassword != null) {
                myAppointmentViewModel.getUserId(userTc, userPassword)
            }
        }
        getMyAppointment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}