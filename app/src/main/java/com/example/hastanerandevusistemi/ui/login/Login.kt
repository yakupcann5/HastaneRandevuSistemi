package com.example.hastanerandevusistemi.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding
import com.example.hastanerandevusistemi.ui.splash.SplashActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Yakup CAN on 24.03.2023
 */
@AndroidEntryPoint
class Login : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.kayitOlText.setOnClickListener(this)
        binding.loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.kayitOlText.id -> {
                findNavController().navigate(R.id.action_login_to_register)
            }
            binding.loginButton.id -> {
                login()
            }
        }
    }

    fun login() {
        if (!binding.tcInput.text.isNullOrEmpty()) {
            if (!binding.passwordInput.text.isNullOrEmpty()) {
                loginViewModel.getUserByTcAndPassword(
                    binding.tcInput.text.toString().toInt(),
                    binding.passwordInput.text.toString()
                )
                loginViewModel.loginState.observe(viewLifecycleOwner) {
                    if (it) {
                        Toast.makeText(context, "giriş başarlı", Toast.LENGTH_SHORT).show()
                        val bundle = Bundle().apply {
                            putInt("tc", binding.tcInput.text.toString().toInt())
                            putString("password", binding.passwordInput.text.toString())
                        }
                        findNavController().navigate(R.id.action_login_to_home2, bundle)
                    } else {
                        Toast.makeText(context, "giriş başarısız", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                binding.passwordInput.error = "Şifre boş bırakılamaz"
            }
        } else {
            binding.tcInput.error = "TC boş bırakılamaz"
        }
    }
}