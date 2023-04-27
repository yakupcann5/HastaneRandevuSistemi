package com.example.hastanerandevusistemi.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Yakup CAN on 24.03.2023
 */

@AndroidEntryPoint
class Register : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.kayitOlText.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.kayitOlText.id -> {
                findNavController().navigate(R.id.action_register_to_login)
            }

            binding.registerButton.id -> {
                observeAddUserInfo()
                register()
            }
        }
    }

    private fun observeAddUserInfo() {
        registerViewModel.addUserModel.observe(this) {
            registerViewModel.checkInput()
        }
    }

    private fun register() {
        registerViewModel.checkInput()
        if (registerViewModel.addUserModelIsFull.value == true) {
            registerViewModel.addUser()
        } else {
            Toast.makeText(context, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
        }
    }
}