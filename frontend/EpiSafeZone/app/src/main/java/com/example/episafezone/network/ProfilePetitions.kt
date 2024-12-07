package com.example.episafezone.network

import android.provider.ContactsContract.Profile
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.BuildConfig
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import org.json.JSONArray
import org.json.JSONObject

object ProfilePetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ProfileFragment.getContext())
    }

    fun getProfileInfo(patient : Patient){
        val patientId = patient.id
        val userId = User.getId()
        println("${url}/patient/info/$patientId/$userId")
        val stringRequest = StringRequest(
            Request.Method.GET, "${url}/patient/info/$patientId/$userId",
            {response->
                Log.d("GetProfileInfoResponse",response.toString())
                ProfileFragment.startProfile(response.toString());
            },
            {error->
                Log.d("GetProfileError",error.message.toString())
            })
        orderVolleyQueue.add(stringRequest)
    }

}