package com.dicetek.firemessaging.translator

import android.content.Context
import com.dicetek.firemessaging.R
import com.dicetek.firemessaging.utils.SystemUtils
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class ArTranslation(private val context: Context) {
    private val englishGermanTranslator by lazy {
        Translation.getClient( TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.ARABIC)
            .build())
    }

    private val networkStatus by lazy {
        SystemUtils(context)
    }

    init {


        englishGermanTranslator.downloadModelIfNeeded()
            .addOnSuccessListener {
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)
            }
            .addOnFailureListener { exception ->
                // Model couldn’t be downloaded or other internal error.
                // ...
            }

    }


   fun  getTranslate(text:String,lam: (String?) ->Unit){

       if(networkStatus.isNetworkConnected()) {

           englishGermanTranslator.translate(text)
               .addOnSuccessListener { translatedText ->
                   lam(translatedText)
               }
               .addOnFailureListener {
                   lam(null)
               }
       }else{
           lam(context.getString(R.string.no_internet))
       }
   }
}