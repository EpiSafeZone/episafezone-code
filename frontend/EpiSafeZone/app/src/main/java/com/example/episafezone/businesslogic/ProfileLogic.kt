package com.example.episafezone.businesslogic

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityProfile.Companion
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.databinding.ActivityProfileBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.ProfilePetitions
import java.time.LocalDate
import java.util.Date


object ProfileLogic {

    private val gson = Gson();

    fun setUpInfo(json : String, binding: ActivityProfileBinding, context: Context){
        val user = gson.fromJson(json,Patient::class.java)
        binding.patientAgeText.text=user.age.toString()
        binding.patientNameText.text = "${user.name} ${user.surname}"
        binding.patientWeightText.text = user.weight.toString() + " kg"
        binding.patientHeigthText.text = user.height + " m"
        val jsonObjet = JSONObject(json)
        val medications = jsonObjet.get("medications") as JSONArray
        setUpMedicationAdapter(medications,binding,context)
    }

    private fun setUpMedicationAdapter(json: JSONArray, binding: ActivityProfileBinding,context: Context){
        val listType = object : TypeToken<List<Medication>>() {}.type
        val medicines : List<Medication> = gson.fromJson(json.toString(),listType)
        binding.medicamentsRecycler.adapter =  MedicationAdapter(medicines,context)
        binding.medicamentsRecycler.layoutManager = LinearLayoutManager(context)
    }

    fun getManifestInfo() {
        // TODO: Cuando este listo descomentar el codigo siguiente y quitar el resto:
        //ManifestationPetitions.getManifestations(patient)
        responseGetManifesInfo(true, emptyList<Manifestation>())
    }

    fun responseGetManifesInfo(success: Boolean,  list: List<Manifestation>){
        // TODO: Cuando este listo descomentar el codigo siguiente y quitar el resto:
        //if(success){
        //    Toast.makeText(ActivityProfile.getContext(), "Información de manifestaciones obtenida correctamente!", Toast.LENGTH_SHORT).show()
        //    return list
        //} else {
        //    Toast.makeText(ActivityProfile.getContext(), "Error al obtener las manifestaciones del paciente. ", Toast.LENGTH_SHORT).show()
        //    ActivityProfile.updateListOfManifestations(emptyList<Manifestation>())
        //}
        val listTemp = ArrayList<Manifestation>();
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        ActivityProfile.updateListOfManifestations(listTemp)
    }
}