package com.example.hastanerandevusistemi.ui.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.databinding.FragmentProfilBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profil : Fragment() {
    private lateinit var binding: FragmentProfilBinding
    private val profilViewModel: ProfilViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(layoutInflater)
        initView()
        getUserInfo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initView() {

    }

    private fun getUserInfo() {
        val userTc = arguments?.getInt("tc")
        val userPassword = arguments?.getString("password")
        profilViewModel.getUserInfo(userTc!!, userPassword!!)
        profilViewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {
                    binding.textViewName.text = it.data?.name
                    binding.textViewSurname.text = it.data?.surname
                    binding.textViewTc.text = it.data?.tc.toString()
                    binding.textViewEmail.text = it.data?.email
                    binding.textViewPassword.text = "***********"
                }
                is RequestState.Error -> {

                }
            }
        }
    }
}