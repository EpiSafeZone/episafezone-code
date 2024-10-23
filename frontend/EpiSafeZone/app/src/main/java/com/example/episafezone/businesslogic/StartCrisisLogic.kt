package com.example.episafezone.businesslogic

import android.content.Intent
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import org.json.JSONObject

object StartCrisisLogic {
    fun getProfileLogic(patient: Patient) {
        StartCrisisPetitions.getProfileInfo(patient)
    }

    fun loadStartCrisis(result: String) {
        ActivityPatientsList.loadStartCrisis(result)
    }
}