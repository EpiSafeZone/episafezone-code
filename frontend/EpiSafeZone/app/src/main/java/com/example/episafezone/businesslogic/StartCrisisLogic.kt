package com.example.episafezone.businesslogic

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.R
import com.example.episafezone.businesslogic.utils.TimerService
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
        ActivityStartCrisis.serviceIntent.putExtra(TimerService.TIMER_EXTRA, ActivityStartCrisis.time)
        ActivityStartCrisis.getContext().startService(ActivityStartCrisis.serviceIntent)
        binding.button.text = "Detener"
        binding.button.setBackgroundColor(getColor(ActivityStartCrisis.getContext(), R.color.red))
        timerStarted = true
    }

    private fun stopTimer(binding: ActivityStartCrisisBinding) {
        // TODO: Implementar logica registrar manifestación
        Toast.makeText(ActivityStartCrisis.getContext(), "Parar timer", Toast.LENGTH_SHORT).show()
    }

    fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(minutes, seconds)
    }

    fun makeTimeString(min: Int, sec: Int): String {
        return String.format("%02d:%02d", min, sec)
    }
}