package com.example.episafezone.businesslogic

import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.fragments.ChronometerFragment
import com.example.episafezone.R
import com.example.episafezone.databinding.FragmentChronometerBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

object ChronometerLogic {

    lateinit var manifestations: MutableList<Manifestation>

    private val gson = Gson()

    private var timerStarted = false

    fun getProfileLogic(patient: Patient) {
        StartCrisisPetitions.getPatientManifestations(patient)
    }

    fun setUpInfo(json: String) {
        val jsonObject= JSONObject(json)
        val manifestationsJSON = jsonObject.getJSONArray("manifestations")
        manifestations = getManifestationList(manifestationsJSON)
        ChronometerFragment.updatePosibleManifestations(manifestations)
    }

    fun getManifestationList(jsonArray:JSONArray): MutableList<Manifestation> {
        val list = mutableListOf<Manifestation>()
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val id = item.getString("id").toInt()
            val name = item.getString("name")
            val description = item.getString("description")
            //TODO: Descomentar la linea de abajo y añadir a la manifestacion cuando se implemente el procedimiento.
            //val procedure = item.getString("procedure")
            val procedure = ""
            list.add(Manifestation(id,name, description,procedure))
        }
        return list
    }

    fun startStopTimer(binding: FragmentChronometerBinding, patient : Patient) {
        if(timerStarted)
            stopTimer(binding,patient)
        else
            startTimer(binding)
    }

    private fun startTimer(binding: FragmentChronometerBinding) {
        timerStarted = true
        ChronometerFragment.chronometer.base = SystemClock.elapsedRealtime()
        ChronometerFragment.chronometer.start()
        binding.button.text = "Detener"
        binding.button.setBackgroundColor(getColor(ChronometerFragment.getContext(), R.color.red))
    }

    private fun stopTimer(binding: FragmentChronometerBinding,patient: Patient) {
        ChronometerFragment.chronometer.stop()
        timerStarted = false
        val elapsedTime = getElapsedTime(binding)
        ChronometerFragment.startCrisisRegister(elapsedTime)
        Toast.makeText(ChronometerFragment.getContext(), "Parar timer", Toast.LENGTH_SHORT).show()
    }

    private fun getElapsedTime(binding: FragmentChronometerBinding): Long {
        val elapsedMillis = SystemClock.elapsedRealtime() - binding.chrono.base
        return elapsedMillis / 1000
    }

    fun makeTimeString(min: Int, sec: Int): String {
        return String.format("%02d:%02d", min, sec)
    }
}