package com.example.episafezone.businesslogic

import android.app.Activity
import android.content.Intent
import android.os.Looper
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
import android.os.Handler
import android.view.Gravity
import android.widget.TextView
import com.example.episafezone.fragments.ChronometerFragment.Companion
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

object ChronometerLogic {

    lateinit var manifestations: MutableList<Manifestation>

    private val gson = Gson()

    private var updateHandler: Handler? = null // Handler para actualizar la ProgressBar
    private var maxTimeInSeconds = 300 // 5 minutos = 300 segundos
    private var timerStarted = false

    fun getTimerStarted(): Boolean {
        return timerStarted
    }

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
            val procedure = item.getString("steps")
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
        MainActivityLogic.EliminateOnClickListenersForTimer()
        timerStarted = true
        ChronometerFragment.chronometer.base = SystemClock.elapsedRealtime()
        ChronometerFragment.chronometer.start()
        startProgressBarUpdate(binding)
        //binding.button.text = "Detener"
        //binding.button.setBackgroundColor(getColor(ChronometerFragment.getContext(), R.color.red))
        binding.button.setImageResource(R.mipmap.pause)
        binding.button.setPadding(11, 16, 0, 0)
        binding.registrarLabel.setText("Registrando...")
    }

    private fun stopTimer(binding: FragmentChronometerBinding,patient: Patient) {
        MainActivityLogic.EnableOnClickListenersForTimer()
        ChronometerFragment.chronometer.stop()
        timerStarted = false
        val elapsedTime = getElapsedTime(binding)
        ChronometerFragment.startCrisisRegister(elapsedTime)
        Toast.makeText(ChronometerFragment.getContext(), "Parar timer", Toast.LENGTH_SHORT).show()
        binding.button.setImageResource(R.mipmap.right_arrow)
        binding.button.setPadding(0, 10, 17, 0)
    }

    private fun startProgressBarUpdate(binding: FragmentChronometerBinding) {
        updateHandler =
            android.os.Handler(Looper.getMainLooper()) // Usamos el Handler principal para actualizar la UI

        updateHandler?.postDelayed(object : Runnable {
            override fun run() {
                if (timerStarted) {
                    val elapsedTimeInSeconds = (SystemClock.elapsedRealtime() - ChronometerFragment.chronometer.base) / 1000
                    val progress = if (elapsedTimeInSeconds < maxTimeInSeconds) {
                        (elapsedTimeInSeconds * 100) / maxTimeInSeconds // Calculamos el porcentaje para la ProgressBar
                    } else {
                        100 // La ProgressBar está llena después de 5 minutos
                    }

                    binding.progressBar.progress = progress.toInt() // Convertimos a Int y actualizamos la ProgressBar

                    // Si no se ha alcanzado el máximo (5 minutos), actualizamos nuevamente
                    if (elapsedTimeInSeconds < maxTimeInSeconds) {
                        updateHandler?.postDelayed(this, 1000)
                    }
                }
            }
        }, 1000) // Empezamos a actualizar después de 1 segundo
    }

    private fun getElapsedTime(binding: FragmentChronometerBinding): Long {
        val elapsedMillis = SystemClock.elapsedRealtime() - binding.chrono.base
        return elapsedMillis / 1000
    }

    fun makeTimeString(min: Int, sec: Int): String {
        return String.format("%02d:%02d", min, sec)
    }

    fun handleManifestationClick(manifestation: Manifestation) {
        val steps = manifestation.procedure.split("\n") // Asumimos que los pasos están separados por saltos de línea

        // Actualizar el RecyclerView con los pasos
        ChronometerFragment.updateStepsRecyclerView(steps)

        // Cambiar el texto de messageTextView a "Pasos a seguir"
        val context = ChronometerFragment.getContext()
        val messageTextView = (context as? Activity)?.findViewById<TextView>(R.id.messageTextView)

        // Cambiar el texto y centrarlo
        messageTextView?.apply {
            text = "Pasos a seguir"
            gravity = Gravity.CENTER // Esto centra el texto dentro del TextView
        }
    }
}