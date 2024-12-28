package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.MainActivity
import com.example.episafezone.businesslogic.ChartLogic
import com.example.episafezone.businesslogic.ChronometerLogic
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import org.json.JSONObject

object ChartPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(MainActivity.getContext())
    }

    fun getNumberOfManifestations(patient: Patient) {
        val patientId = patient.id
        val stringRequest = StringRequest(
            Request.Method.GET, "${url}/patient/numMani/$patientId",
            {response->
                val json = JSONObject(response)
                ChartLogic.collectDataPieChart(json)
            },
            {error->
                Log.d("GetPatientManifestations",error.message.toString())
            })
        orderVolleyQueue.add(stringRequest)
    }

    fun getNumberOfCrisis(patient: Patient) {
        val patientId = patient.id
        val stringRequest = StringRequest(
            Request.Method.GET, "${url}/patient/crisisWeek/$patientId",
            {response->
                val json = JSONObject(response)
                ChartLogic.collectDataLineChart(json)
            },
            {error->
                Log.d("GetPatientManifestations",error.message.toString())
            })
        orderVolleyQueue.add(stringRequest)
    }
}