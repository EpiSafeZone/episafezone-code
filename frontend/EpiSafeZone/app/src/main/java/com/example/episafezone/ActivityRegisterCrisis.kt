package com.example.episafezone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.databinding.ActivityRegisterCrisisBinding
import java.time.LocalDate

class ActivityRegisterCrisis : AppCompatActivity()  {
    private lateinit var binding: ActivityRegisterCrisisBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterCrisisBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val fromChrono = intent.getBooleanExtra("chrono",false)

        if(fromChrono){
            val duration = intent.getLongExtra("time",0)
            binding.duration.setText(duration.toString())
            val date = LocalDate.now()
            binding.date.setText(date.toString())
        }

        binding.acceptCrisisButt.setOnClickListener{

        }

        binding.cancelCrisisButt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("load", "profile")
        }
    }
}