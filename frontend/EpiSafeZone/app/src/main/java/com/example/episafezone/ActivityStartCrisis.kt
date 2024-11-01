package com.example.episafezone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentHostCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.DisplayPossibleManifestations
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson
import java.util.Timer
import java.util.TimerTask

class ActivityStartCrisis : AppCompatActivity() {

    private lateinit var patient: Patient

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartCrisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextObj = this

        val json = intent.getSerializableExtra("patient") as String
        patient = gson.fromJson(json,Patient::class.java)

        chronometer = binding.chrono

        StartCrisisPetitions.initializeQueue();

        StartCrisisLogic.getProfileLogic(patient)

        binding.button.setOnClickListener {
            StartCrisisLogic.startStopTimer(binding)
        }
    }

    companion object{
        private lateinit var contextObj: Context
        private lateinit var binding: ActivityStartCrisisBinding
        private lateinit var manifestations: MutableList<Manifestation>

        lateinit var chronometer: Chronometer

        var time = 0.0

        fun getContext() : Context {
            return contextObj
        }

        fun getBinding() : ActivityStartCrisisBinding {
            return binding
        }

        fun updatePosibleManifestations(list: MutableList<Manifestation>) {
            manifestations = list
            binding.informationScrollView.adapter = DisplayPossibleManifestations(contextObj, list)
            binding.informationScrollView.layoutManager = LinearLayoutManager(contextObj)
        }
    }
}