package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.models.Patient
import org.json.JSONObject

object StartCrisisPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityStartCrisis.getContext())
    }

    fun getPatientManifestations(patient: Patient) {
        val id = patient.id
        val stringRequest = StringRequest(
            Request.Method.GET, "${url}/patient/info/$id",
            {response->
                StartCrisisLogic.setUpInfo(response.toString())
            },
            {error->
                Log.d("GetPatientManifestations",error.message.toString())
            })
        orderVolleyQueue.add(stringRequest)
    }
}