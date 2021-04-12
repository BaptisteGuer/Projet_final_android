package com.eseo.projet_final_android.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.eseo.projet_final_android.ui.MainActivity
import com.eseo.projet_final_android.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }, 4000)
    }
}