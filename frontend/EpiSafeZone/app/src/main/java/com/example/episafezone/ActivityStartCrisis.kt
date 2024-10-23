package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson

class ActivityStartCrisis : AppCompatActivity() {

    private lateinit var patient: Patient

    private lateinit var binding: ActivityStartCrisisBinding

    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartCrisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextObj = this

        val json = intent.getSerializableExtra("json") as String
        setUpInfo(json)
        patient = gson.fromJson(json,Patient::class.java)
        


        StartCrisisPetitions.initializeQueue();

        StartCrisisLogic.getProfileLogic(patient)
    }

    companion object{
        private lateinit var contextObj: Context

        fun getContext() : Context {
            return contextObj
        }
    }
}