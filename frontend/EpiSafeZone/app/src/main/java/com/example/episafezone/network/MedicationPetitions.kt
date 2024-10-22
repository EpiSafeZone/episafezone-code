package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivityProfile
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Patient
import org.json.JSONObject

object MedicationPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityProfile.getContext())
    }
    ////TODO("Terminar endpoint")
    fun addMedication(medication : Medication,patient: Patient) {
        val json = JSONObject()
        json.put("name", medication.name)
        json.put("dosis",medication.dosis)
        json.put("unit",medication.unit)
        json.put("alarm",medication.alarm)
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "${url}/medication", json,
            { response ->
                PatientsListLogic.getProfileInfo(patient)
            },
            { error ->
                println("Error in addMedication: "+error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }
}