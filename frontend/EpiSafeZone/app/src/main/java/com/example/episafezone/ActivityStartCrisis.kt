package com.example.episafezone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentHostCallback
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.businesslogic.utils.TimerService
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson
import java.util.Timer
import java.util.TimerTask

class ActivityStartCrisis : AppCompatActivity() {

    private lateinit var patient: Patient

    private val gson = Gson()

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIMER_EXTRA, 0.0)
            binding.chronoTextView.text = StartCrisisLogic.getTimeStringFromDouble(time)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartCrisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextObj = this

        serviceIntent = Intent(this, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

        val json = intent.getSerializableExtra("patient") as String
        patient = gson.fromJson(json,Patient::class.java)

        StartCrisisPetitions.initializeQueue();

        StartCrisisLogic.getProfileLogic(patient)

        binding.button.setOnClickListener {
            StartCrisisLogic.startStopTimer(binding)
        }
    }

    companion object{
        private lateinit var contextObj: Context
        private lateinit var binding: ActivityStartCrisisBinding
        lateinit var serviceIntent: Intent

        var time = 0.0

        fun getContext() : Context {
            return contextObj
        }

        fun getBinding() : ActivityStartCrisisBinding {
            return binding
        }
    }
}