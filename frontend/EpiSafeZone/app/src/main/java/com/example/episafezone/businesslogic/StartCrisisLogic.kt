package com.example.episafezone.businesslogic

import android.content.Intent
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.databinding.ActivityStartCrisisBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions
import org.json.JSONArray
import org.json.JSONObject

object StartCrisisLogic {
    fun getProfileLogic(patient: Patient) {
        StartCrisisPetitions.getProfileInfo(patient)
    }

    fun loadStartCrisis(result: String) {
        ActivityPatientsList.loadStartCrisis(result)
    }

    fun setUpInfo(json: String, binding : ActivityStartCrisisBinding) {
        //TODO: Poner la información desde el json ejemplo:
        //val user = ProfileLogic.gson.fromJson(json,Patient::class.java)
        //ActivityProfile.updatePatienInf(user)
        //val jsonObject = JSONObject(json)
        //val medications = jsonObject.get("medications") as JSONArray
        //val manifests = jsonObject.get("manifestations") as JSONArray
        //ProfileLogic.setUpMedicationAdapter(medications)
        //ProfileLogic.setUpManifestationAdapter(manifests)
    }
}