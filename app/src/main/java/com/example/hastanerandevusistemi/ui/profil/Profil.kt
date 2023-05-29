package com.example.hastanerandevusistemi.ui.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.databinding.FragmentProfilBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profil : Fragment(), View.OnClickListener {
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
        binding.logoutButton.setOnClickListener(this)
    }

    private fun getUserInfo() {
        val userTc = arguments?.getLong("tc")
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

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.logoutButton.id -> {
                logout()
            }
        }
    }
    private fun logout() {
        profilViewModel.logout()
        profilViewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {
                    if (it.data == null) {
                        findNavController().navigate(R.id.action_profil2_to_login)
                    }
                }
                is RequestState.Error -> {

                }
            }
        }
    }
}