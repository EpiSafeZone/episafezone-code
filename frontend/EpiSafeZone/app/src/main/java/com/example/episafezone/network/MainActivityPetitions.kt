package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.MainActivity
import com.example.episafezone.businesslogic.MainActivityLogic
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.models.Device
import com.example.episafezone.models.User
import org.json.JSONObject

object MainActivityPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(MainActivity.getContext())
    }

    fun saveDevice(device : Device){
        val json = JSONObject()
        json.put("token",device.token)
        json.put("user",device.user)
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "${url}/device/create", json,
            {response-> },
            {error->
                println(error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }

    fun getPatientsList(){
        val userId = User.getId()
        val jsonRequest = StringRequest(
            Request.Method.GET, "$url/tutor/list/$userId",
            {response->
                MainActivityLogic.ProcessPatientsList(response.toString())
            },
            {error->
                println(error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }
}