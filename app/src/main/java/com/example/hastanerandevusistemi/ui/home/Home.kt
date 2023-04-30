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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.profil.setOnClickListener(this)
        binding.randevuAl.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.profil.id -> {
                val bundle = Bundle().apply {
                    putInt("tc", arguments?.getInt("tc")!!)
                    putString("password", arguments?.getString("password")!!)
                }
                findNavController().navigate(R.id.action_home2_to_profil2, bundle)
            }
            binding.randevuAl.id -> {
                val bundle = Bundle().apply {
                    putInt("tc", arguments?.getInt("tc")!!)
                    putString("password", arguments?.getString("password")!!)
                }
                findNavController().navigate(R.id.action_home2_to_makeAppointment, bundle)
            }
        }
    }
}