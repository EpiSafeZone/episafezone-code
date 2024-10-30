package com.example.episafezone.businesslogic

import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.R
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

object StartCrisisLogic {

    private val gson = Gson()

    private var timerStarted = false

    fun getProfileLogic(patient: Patient) {
        StartCrisisPetitions.getProfileInfo(patient)
    }

    fun setUpInfo(json: String, binding : ActivityStartCrisisBinding) {
        //TODO: Poner la información desde el json, ejemplo:
        val user = gson.fromJson(json,Patient::class.java)
        val jsonObject = JSONObject(json)
        val medications = jsonObject.get("medications") as JSONArray
        val manifests = jsonObject.get("manifestations") as JSONArray
        //ActivityProfile.updatePatienInf(user)
        //ProfileLogic.setUpMedicationAdapter(medications)
        //ProfileLogic.setUpManifestationAdapter(manifests)
    }

    fun startStopTimer(binding: ActivityStartCrisisBinding) {
        if(timerStarted)
            stopTimer(binding)
        else
            startTimer(binding)
    }

    private fun startTimer(binding: ActivityStartCrisisBinding) {
        binding.button.text = "Detener"
        binding.button.setBackgroundColor(getColor(ActivityStartCrisis.getContext(), R.color.red))
        timerStarted = true
        ActivityStartCrisis.chronometer.base = SystemClock.elapsedRealtime()
        ActivityStartCrisis.chronometer.start()
    }

    private fun stopTimer(binding: ActivityStartCrisisBinding) {
        ActivityStartCrisis.chronometer.stop()
        timerStarted = false
        val elapsedTime = getElapsedTime(binding)
        // TODO: Implementar logica registrar manifestación
        Toast.makeText(ActivityStartCrisis.getContext(), "Parar timer", Toast.LENGTH_SHORT).show()
    }

    private fun getElapsedTime(binding: ActivityStartCrisisBinding): Long {
        val elapsedMillis = SystemClock.elapsedRealtime() - binding.chrono.base
        return elapsedMillis / 1000
    }

    fun makeTimeString(min: Int, sec: Int): String {
        return String.format("%02d:%02d", min, sec)
    }
}