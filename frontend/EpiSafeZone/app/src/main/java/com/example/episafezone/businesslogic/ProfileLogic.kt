package com.example.episafezone.businesslogic

import android.content.Context
import android.provider.ContactsContract.Profile
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.models.SharedUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject


object ProfileLogic {

    private val gson = Gson();

    fun setUpInfo(json : String){
        val user = gson.fromJson(json,Patient::class.java)
        ProfileFragment.updatePatienInf(user)
        val jsonObject = JSONObject(json)
        val medications = jsonObject.get("medications") as JSONArray
        val manifests = jsonObject.get("manifestations") as JSONArray
        val shared = jsonObject.get("sharedTutors") as JSONArray
        setUpMedicationAdapter(medications)
        setUpManifestationAdapter(manifests)
        setUpSharedAdapter(shared)
    }

    private fun setUpMedicationAdapter(json: JSONArray){
        val listType = object : TypeToken<MutableList<Medication>>() {}.type
        val medicines : MutableList<Medication> = gson.fromJson(json.toString(),listType)
        ProfileFragment.updateListOfMedications(medicines)
    }

    private fun setUpManifestationAdapter(json: JSONArray){
        val listType = object : TypeToken<MutableList<Manifestation>>() {}.type
        val manifests : MutableList<Manifestation> = gson.fromJson(json.toString(),listType)
       ProfileFragment.updateListOfManifestations(manifests)
    }

    private fun setUpSharedAdapter(json:JSONArray){
        val listType = object : TypeToken<MutableList<SharedUser>>() {}.type
        val shared : MutableList<SharedUser> = gson.fromJson(json.toString(),listType)
        ProfileFragment.updateListOfShared(shared)
    }
}