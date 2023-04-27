package com.example.hastanerandevusistemi.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Splash : AppCompatActivity() {
    private val splashActivityViewModel: SplashActivityViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (splashActivityViewModel.firstOpen()) {
            getData()
        } else {
            splashActivityViewModel.getCityItemCount()
            splashActivityViewModel.itemCityCount.observe(this) {
                if (!it) {
                    getData()
                }
            }
        }
    }

    private fun getData() {
        Log.d("süre", "getData: ${System.currentTimeMillis()}")
        splashActivityViewModel.setCityData()
        splashActivityViewModel.setDistrictData()
        splashActivityViewModel.setHospitalData()
        splashActivityViewModel.setPolyclinicData()
        splashActivityViewModel.setDoctorData()
        splashActivityViewModel.setDaysData()
        splashActivityViewModel.setHourData()
        Log.d("süre", "getData: ${System.currentTimeMillis()}")
    }
}