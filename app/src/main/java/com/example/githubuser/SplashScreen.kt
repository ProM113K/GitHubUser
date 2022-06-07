package com.example.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val splash = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(splash)
            finish()
        }, SPLASH_DURATION)
    }

    companion object {
        private const val SPLASH_DURATION = 3000L
    }
}