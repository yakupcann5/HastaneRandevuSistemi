package com.example.hastanerandevusistemi.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.example.hastanerandevusistemi.MainActivity
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Splash : AppCompatActivity() {
    private val splashActivityViewModel: SplashActivityViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        startAnimation()
        setContentView(binding.root)
        if (splashActivityViewModel.firstOpen()) {
            getData()
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 5000)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 5000)
        }
    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                startAnimation() // Animasyon bittiğinde tekrar başlat
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.logo.startAnimation(anim)
    }

    private fun getData() {
        splashActivityViewModel.setCityData()
        splashActivityViewModel.setDistrictData()
        splashActivityViewModel.setHospitalData()
        splashActivityViewModel.setPolyclinicData()
        splashActivityViewModel.setDoctorData()
        splashActivityViewModel.setDaysData()
        splashActivityViewModel.setHourData()
    }
}