package com.example.episafezone.businesslogic

import android.content.Intent
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.util.Timer
import java.util.TimerTask

object StartCrisisLogic {

    private lateinit var timerTask : TimerTask

    private val gson = Gson()

    private var timerStarted = false

    private var timer = Timer()

    private var time = 0.0

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
        if (timerStarted) {
            timerStarted = false
            binding.button.text = "Detener"
            //TODO: Continuar logica
            // https://www.youtube.com/watch?v=7QVr5SgpVog
        } else {
            timerStarted = true
            binding.button.text = "Empezar"
            //TODO: Continuar logica
        }
    }
}