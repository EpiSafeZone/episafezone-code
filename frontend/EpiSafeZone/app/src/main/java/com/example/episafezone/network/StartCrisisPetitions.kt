package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.MainActivity
import com.example.episafezone.businesslogic.ChronometerLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import org.json.JSONObject
import java.time.LocalDate
import java.time.ZoneId

object StartCrisisPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(MainActivity.getContext())
    }

    fun getPatientManifestations(patient: Patient) {
        val patientId = patient.id
        val userId = User.getId()
        val stringRequest = StringRequest(
            Request.Method.GET, "${url}/patient/info/$patientId/$userId",
            {response->
                ChronometerLogic.setUpInfo(response.toString())
            },
            {error->
                Log.d("GetPatientManifestations",error.message.toString())
            })
        orderVolleyQueue.add(stringRequest)
    }

    fun registerCrisis(crisis: Crisis){
        val localDate: LocalDate = crisis.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val jsonObject = JSONObject()
        jsonObject.put("duration", crisis.duration)
        jsonObject.put("date", localDate)
        jsonObject.put("hour", crisis.hour)
        jsonObject.put("context", crisis.context)
        jsonObject.put("emergency", crisis.emergency)
        jsonObject.put("manifestation", crisis.manifestationId)
        jsonObject.put("patient", crisis.patient)
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "${url}/crisis/create", jsonObject,
            {response->
                StartCrisisLogic.SuccessfullRegisterCrisis()
            },
            {error->
                StartCrisisLogic.FailedRegisterCrisis()
            })

        orderVolleyQueue.add(jsonRequest)
    }

    fun sendNotification(patient : Patient){
        println("se ha llamado a la función")
        val jsonObject = JSONObject()
        jsonObject.put("type","crisis")
        jsonObject.put("subtype", "started")
        val stringRequest = StringRequest(
            Request.Method.POST,
            "${url}/patient/notify/${patient.id}?type=crisis&subtype=started",
            { response -> },
            { error -> }
        )

        orderVolleyQueue.add(stringRequest)

    }
}