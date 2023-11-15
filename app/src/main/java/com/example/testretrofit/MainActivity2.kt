package com.example.testretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.testretrofit.viewmodels.MainViewModel

class MainActivity2 : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var et1:EditText
    private lateinit var ed:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        et1 = findViewById(R.id.et1)

    }

}