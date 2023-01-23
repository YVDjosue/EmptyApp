package com.wanku.emptyapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import com.wanku.emptyapp.databinding.ActivityWidgetsBinding
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.*

class WidgetsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWidgetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWidgetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var webView = binding.webView
        webView.settings.javaScriptEnabled
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")

        var vvLocal = binding.vvLocal
        var mcLocal = MediaController(this)
        mcLocal.setAnchorView(vvLocal)
        val path = "android.resource://" + packageName + "/" + R.raw.chichenol
        vvLocal.setVideoURI(Uri.parse(path))
        vvLocal.setMediaController(mcLocal)

        var vvInternet = binding.vvInternet
        var mcInternet = MediaController(this)
        mcInternet.setAnchorView(vvInternet)
        vvInternet.setVideoPath("https://wankusoftware.com/video/chichenol.mp4")
        vvInternet.setMediaController(mcInternet)

        var calendarView = binding.calendarView
        var tvDate = binding.tvDate

        calendarView.setOnDateChangeListener { cv, year, month, day ->
            var date = "$day/${month + 1}/$year"
            tvDate.text = date
        }
        var calendar = Calendar.getInstance()
        calendar.set(2025, 1, 17)
        calendarView.date = calendar.timeInMillis

        var progressBarCircular = binding.progressBar
        var progressBarHorizontal = binding.progressBar2

        progressBarCircular.max = 2000
        progressBarCircular.progress = 0

        progressBarHorizontal.max = 1000
        progressBarHorizontal.progress = 0

        GlobalScope.launch {
            updateProgressBar(progressBarCircular)
        }

        GlobalScope.launch {
            updateProgressBar(progressBarHorizontal)
        }

        var ratingBar = binding.ratingBar
        ratingBar.max = 8
        ratingBar.numStars = 8
        ratingBar.rating = 6f
        ratingBar.setIsIndicator(false)
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(this,"Nivel actual: $fl",Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun updateProgressBar(progressBar:ProgressBar){
        while (progressBar.progress<progressBar.max){
            delay(100)
            progressBar.incrementProgressBy(10)
        }
    }

}