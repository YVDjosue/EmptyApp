package com.wanku.emptyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.size
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.stripe.android.PaymentConfiguration
import com.wanku.emptyapp.databinding.ActivityButtonsBinding
class ButtonsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButtonsBinding
    private var interstitialAd : InterstitialAd?=null
    var TAG = "msg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PaymentConfiguration.init(
            applicationContext,
            "pk_test_Dt4ZBItXSZT1EzmOd8yCxonL"
        )


        var adRequest =AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",adRequest,object :InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
                //super.onAdFailedToLoad(p0)
                Toast.makeText(this@ButtonsActivity,"No se pudo cargar la publicidad",Toast.LENGTH_SHORT).show()
                interstitialAd=null
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                Toast.makeText(this@ButtonsActivity,"Si se pudo cargar la publicidad",Toast.LENGTH_SHORT).show()
                interstitialAd=p0
            }
        })

        interstitialAd?.fullScreenContentCallback=object: FullScreenContentCallback(){
            override fun onAdClicked() {
                Log.d(TAG,"Hiciste click en el anuncio")
            }

            override fun onAdDismissedFullScreenContent() {
                Toast.makeText(this@ButtonsActivity,"Cerraste el Ad",Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Cerraste el anuncio")
                interstitialAd=null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                Log.d(TAG,"Hubo un error al mostrar el anuncio")
                interstitialAd=null
            }

            override fun onAdImpression() {
                Log.d(TAG,"Se pudo imprimir el anuncio")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG,"Mostrando anuncio en pantalla completa")
            }
        }

        var chipGroup = binding.chipGroup
        var chip: Chip
        var button = binding.button
        var radioGroup = binding.radioGroup

        button.text="Modificado"
        button.setOnClickListener {
            showAd()
        }

        for (i in 0 until chipGroup.childCount){
            chip = chipGroup.getChildAt(i) as Chip
            chip.setOnCloseIconClickListener {
                chipGroup.removeView(it)
            }
            chip.setOnClickListener {
                var temp = it as Chip
                Toast.makeText(this,"${temp.text} pulsado!",Toast.LENGTH_SHORT).show()
            }
        }

        var radioButton = radioGroup.getChildAt(1) as RadioButton
        radioButton.text="mujer"
        radioGroup.check(radioGroup.getChildAt(0).id)

    }

    fun showAd(){
        if(interstitialAd!=null){
            interstitialAd?.show(this)
        }
        else{
            Log.d(TAG,"El anuncio aun no se ha cargado")
        }
    }
    fun initPay(view:View){
        val intent = Intent(this,CheckoutActivity::class.java)
        startActivity(intent)
    }

}