package com.krillinator.servicetest2

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.krillinator.servicetest2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceClass = RandomNumberService::class.java      // Service Class name variable
        val intent = Intent(applicationContext, serviceClass)   // Intent instance

        binding.btnService.setOnClickListener() {
            if (!isServiceRunning(serviceClass)) {
                startService(intent)
            } else {
                Toast.makeText(applicationContext, "Service is already running", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }


























}