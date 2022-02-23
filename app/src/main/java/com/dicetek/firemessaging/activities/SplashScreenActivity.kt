package com.dicetek.firemessaging.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.dicetek.firemessaging.R
import com.dicetek.firemessaging.databinding.ActivityMainBinding
import com.dicetek.firemessaging.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenActivity by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(splashScreenActivity.root)

        supportActionBar?.hide()

        lifecycleScope.launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            Intent(this@SplashScreenActivity,MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }

    }
}