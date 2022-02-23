package com.dicetek.firemessaging.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicetek.firemessaging.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

abstract class BaseActivity:AppCompatActivity() {
    lateinit var remoteConfig: FirebaseRemoteConfig
    abstract fun getContentLayout(): View
    abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0

        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)


        onCreate()
        setContentView(getContentLayout())



    }



    protected fun showToast(msg: String?,duration:Int =  Toast.LENGTH_SHORT) {
        Toast.makeText(baseContext, msg, duration).show()
    }
}