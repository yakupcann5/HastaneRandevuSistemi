package com.example.hastanerandevusistemi.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    var userId: Long = 0
    var userPassword: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initView()
        homeViewModel.getUserInfo()
        observeLiveData()
        return binding.root
    }

    private fun observeLiveData() {
        homeViewModel.userId.observe(viewLifecycleOwner) {
            userId = it
        }
        homeViewModel.userPassword.observe(viewLifecycleOwner) {
            userPassword = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.profil.setOnClickListener(this)
        binding.randevuAl.setOnClickListener(this)
        binding.randevularM.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.profil.id -> {
                val bundle = Bundle().apply {
                    putLong("tc", userId)
                    putString("password", userPassword)
                }
                findNavController().navigate(R.id.action_home2_to_profil2, bundle)
            }
            binding.randevuAl.id -> {
                val bundle = Bundle().apply {
                    putLong("tc", userId)
                    putString("password", userPassword)
                }
                findNavController().navigate(R.id.action_home2_to_makeAppointment, bundle)
            }
            binding.randevularM.id -> {
                val bundle = Bundle().apply {
                    putLong("tc", userId)
                    putString("password", userPassword)
                }
                findNavController().navigate(R.id.action_home2_to_myAppointment, bundle)
            }
        }
    }
}