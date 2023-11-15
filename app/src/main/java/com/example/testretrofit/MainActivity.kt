package com.example.testretrofit

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.testretrofit.apii.RetrofitHelper
import com.example.testretrofit.apii.WeatherService
import com.example.testretrofit.repository.WeatherRepository
import com.example.testretrofit.viewmodels.MainViewModel
import com.example.testretrofit.viewmodels.MainViewModelFactory
import soup.neumorphism.NeumorphCardView

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)
        val et1 = findViewById<EditText>(R.id.et1)
        val bt1 = findViewById<Button>(R.id.Button1)
        findViewById<LinearLayout>(R.id.gi)
        val conditionText = findViewById<TextView>(R.id.cdt_text)
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)

        val cardView = findViewById<NeumorphCardView>(R.id.cardView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val imgView = findViewById<ImageView>(R.id.img)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.anim_loading)
        lottieAnimationView.repeatCount = LottieDrawable.INFINITE

        val weatherService = RetrofitHelper.getInstance().create(WeatherService::class.java)
        val repository = WeatherRepository(weatherService)

        Handler(Looper.getMainLooper())

        bt1.setOnClickListener {
            val city = et1.text.toString()
            if (city.isNotEmpty()) {
                lottieAnimationView.visibility = View.VISIBLE
                lottieAnimationView.playAnimation()

                cardView.visibility = View.GONE
                imgView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                bt1.visibility = View.GONE

                mainViewModel.getWeatherData(city)
            } else {
                et1.error = "Please Enter Correct City"
            }
        }

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.weather.observe(this) { weatherData ->
            progressBar.visibility = View.GONE
            bt1.visibility = View.VISIBLE
            cardView.visibility = View.VISIBLE
            imgView.visibility = View.VISIBLE
            lottieAnimationView.cancelAnimation()
            lottieAnimationView.visibility = View.GONE

            val cityName = weatherData.location.name
            val stateName = weatherData.location.region
            val region = "$cityName, $stateName"
            val imageLink = "https:${weatherData.current.condition.icon}"
            tv1.text = weatherData.current.temp_c.toString()
            tv2.text = region
            tv3.text = weatherData.location.localtime
            Glide.with(this)
                .load(imageLink)
                .into(imgView)
            conditionText.text = weatherData.current.condition.text
            Log.i("ak", weatherData.toString())
        }
    }
}
