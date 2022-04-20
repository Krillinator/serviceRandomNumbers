package com.krillinator.servicetest2

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import java.lang.UnsupportedOperationException
import java.util.*

class RandomNumberService : Service() {

    private lateinit var mHandler: Handler      // Process that will run continuously
    private lateinit var mRunnable: Runnable    // Object with functions that will be run

    // Not needed - throw or return null
    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    // Main logic
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /* TOASTED */   Toast.makeText(applicationContext, "Service started", Toast.LENGTH_SHORT).show()

        mHandler = Handler(Looper.getMainLooper())
        mRunnable = Runnable { showRandomNumber() }

            // Handler().postDelayed(mRunnable, 5000) DEPRECATED
        Handler(Looper.getMainLooper()).postDelayed(mRunnable, 5000)    // Will loop
        println("TEST")                                                          // Will run once

        return START_STICKY     // If process gets killed, restart from here

    }

    // Stop process
    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)

        /* TOASTED */   Toast.makeText(applicationContext, "Random number: $number", Toast.LENGTH_SHORT).show()

        mHandler.postDelayed(mRunnable, 5000)
    }
}