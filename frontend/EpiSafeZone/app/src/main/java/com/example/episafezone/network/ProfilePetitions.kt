package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.ProfileActivity
import com.example.episafezone.models.Patient
import org.json.JSONArray
import org.json.JSONObject

object ProfilePetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ProfileActivity.getContext())
    }

    fun getProfileInfo(patient : Patient){
        val name = patient.name
        val json = JSONObject()
        json.put("name", name)
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "$url/patient/$name", json,
            {response->
                val res = JSONArray(response)
            },
            {error->

            })
    }
}