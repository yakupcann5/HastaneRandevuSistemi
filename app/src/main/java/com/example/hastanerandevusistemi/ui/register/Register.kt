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
                register()
            }
        }
    }

    private fun register() {
        if (!binding.isimInput.text.isNullOrEmpty()) {
            if (!binding.soyisimInput.text.isNullOrEmpty()) {
                if (!binding.tcInput.text.isNullOrEmpty()) {
                    if (!binding.emailInput.text.isNullOrEmpty()) {
                        if (!binding.passwordInput.text.isNullOrEmpty()) {
                            registerViewModel.addUser(
                                binding.isimInput.text.toString(),
                                binding.soyisimInput.text.toString(),
                                binding.tcInput.text.toString(),
                                binding.emailInput.text.toString(),
                                binding.passwordInput.text.toString())
                        }
                    }
                }
            }
        }
        registerViewModel.registerState.observe(this) {
            when (it) {
                true -> {
                    Toast.makeText(context, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_register_to_login)
                }
                false -> {
                    Toast.makeText(context, "Kayıt Başarısız", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}