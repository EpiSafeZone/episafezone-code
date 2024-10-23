package com.example.episafezone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.databinding.ActivityStartCrisisBinding

class ActivityStartCrisis : AppCompatActivity() {

    private lateinit var binding: ActivityStartCrisisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartCrisisBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}