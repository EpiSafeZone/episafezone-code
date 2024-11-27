package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.episafezone.ui.theme.EpiSafeZoneTheme

class MainActivity : ComponentActivity() {

    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this
    }

    companion object {
        public lateinit var contextObj: Context
    }
}