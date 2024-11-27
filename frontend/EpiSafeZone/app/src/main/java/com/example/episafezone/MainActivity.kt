package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.episafezone.databinding.MainActivityBinding
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate();
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