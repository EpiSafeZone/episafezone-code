package com.example.episafezone.businesslogic

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityProfile.Companion
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.databinding.ActivityProfileBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject


object ProfileLogic {

    private val gson = Gson();

    fun setUpInfo(json : String){
        val user = gson.fromJson(json,Patient::class.java)
        ActivityProfile.updatePatienInf(user)
        val jsonObject = JSONObject(json)
        val medications = jsonObject.get("medications") as JSONArray
        val manifests = jsonObject.get("manifestations") as JSONArray
        setUpMedicationAdapter(medications)
        setUpManifestationAdapter(manifests)
    }

    private fun setUpMedicationAdapter(json: JSONArray){
        val listType = object : TypeToken<MutableList<Medication>>() {}.type
        val medicines : MutableList<Medication> = gson.fromJson(json.toString(),listType)
        ActivityProfile.updateListOfMedications(medicines)
    }

    private fun setUpManifestationAdapter(json: JSONArray){
        val listType = object : TypeToken<MutableList<Manifestation>>() {}.type
        val manifests : MutableList<Manifestation> = gson.fromJson(json.toString(),listType)
       ActivityProfile.updateListOfManifestations(manifests)
    }
}