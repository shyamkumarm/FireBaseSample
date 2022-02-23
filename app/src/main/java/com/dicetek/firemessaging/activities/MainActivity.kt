package com.dicetek.firemessaging.activities

import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import com.dicetek.firemessaging.R
import com.dicetek.firemessaging.databinding.ActivityMainBinding
import com.dicetek.firemessaging.translator.ArTranslation
import com.dicetek.firemessaging.utils.Constants
import com.dicetek.firemessaging.utils.Constants.TAG
import com.google.firebase.remoteconfig.ktx.get
import com.google.mlkit.nl.translate.TranslateLanguage
import java.util.*

class MainActivity : BaseActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var  translateLanguage:ArTranslation

  override fun getContentLayout(): View = binding.root

    override fun onCreate() {
        getFromServer()

        initView()




    }
    private fun getFromServer() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    String.format(getString(R.string.data_updated).plus(" $updated"))
                }
                updateUI()
            }
    }


    private fun updateUI() {
        val toShowBanner = remoteConfig[Constants.SHOW_BANNER].asBoolean()
        binding.imageView.isInvisible = !toShowBanner

       val bannerMsg = remoteConfig[Constants.BANNER_MESSAGE].asString()
        if(Locale.getDefault().language == TranslateLanguage.ARABIC){
            translateLanguage.getTranslate(bannerMsg){
                binding.textView.text = it
            }
        }else{
            binding.textView.text = bannerMsg
        }
        Log.d(TAG, "toShowBanner : $toShowBanner")
        Log.d(TAG, "bannerMsg : $bannerMsg")

    }


    private fun initView() {
        binding.button.setOnClickListener {
            getFromServer()
        }
        translateLanguage = ArTranslation(baseContext)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}