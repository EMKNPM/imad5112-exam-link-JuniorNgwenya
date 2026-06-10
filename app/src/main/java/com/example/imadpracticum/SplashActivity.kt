/*
 * FULL NAME: [Insert Your Full Name]
 * STUDENT NUMBER: [Insert Your Student Number]
 * MODULE: IMAD5112 - Introduction to Mobile Application Development
 */

package com.example.imadpracticum

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    // Tag for logging
    companion object {
        private const val TAG = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Use a Handler to delay transition to Main Screen by 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d(TAG, "Splash finished. Navigating to Main Screen.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close splash so user can't go back to it
        }, 3000) // 3000ms = 3 seconds
    }
}
