package com.example.episafezone.network

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.ActivityProfile
import com.example.episafezone.businesslogic.ManifestationLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import org.json.JSONObject

object ManifestationPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityProfile.getContext())
    }

    //TODO: Add as a parameter of this method a reference to the patient
    fun getManifestations(patient : Patient) {
        var id = patient.id
        //TODO: Ajustar el endpoint
        val stringRequest = StringRequest (
            Request.Method.GET, "$url/patient/info/$id/manifestations",
            { response ->
                val res = emptyList<Manifestation>()
                //TODO: Añadir los valores a res.
                ProfileLogic.responseGetManifesInfo(true, res)
            },
            { error ->
                ProfileLogic.responseGetManifesInfo(false, emptyList<Manifestation>())
            }
        )
    }

    fun addManifestation(manifestation: Manifestation) {
        val json = JSONObject()
        json.put("name", manifestation.name)
        //TODO: Terminar el JSON y ajustar el endpoint
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "$url/manifestation", json,
            { response ->
                ManifestationLogic.responseRegisterManifestation(true)
            },
            { error ->
                ManifestationLogic.responseRegisterManifestation(false)
            })
        orderVolleyQueue.add(jsonRequest)
    }

    fun editManifestation(manifestationToModify: Manifestation,
                          modifiedManifestation: Manifestation) {
        val json = JSONObject()
        json.put("name", modifiedManifestation.name)
        //TODO: Terminal el JSON y ajustar el endpoint
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "$url/manifestation", json,
            { response ->
                ManifestationLogic.responseEditManifestation(true)
            },
            { error ->
                ManifestationLogic.responseEditManifestation(false)
            })
    }
}