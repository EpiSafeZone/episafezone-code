package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this
    }

    companion object {
        private lateinit var contextObj: Context

        fun getContext() : Context {
            return contextObj
        }
    }
}