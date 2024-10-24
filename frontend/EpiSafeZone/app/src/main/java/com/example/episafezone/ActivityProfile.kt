package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.ActivityProfileBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.MedicationPetitions
import com.example.episafezone.network.ProfilePetitions


class ActivityProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root)
        patient = intent.getSerializableExtra("patient") as Patient
        contextObj=this;

        val json = intent.getSerializableExtra("json") as String
        ProfileLogic.setUpInfo(json)

        binding.addMedButt.setOnClickListener(){
            val intent = Intent(this,ActivityAddMedication::class.java)
            intent.putExtra("patient",patient)
            startActivity(intent)
        }

        binding.addManifButt.setOnClickListener{
            val intent = Intent(this,ActivityRegisterManifestation::class.java)
            startActivity(intent)
        }

        ProfilePetitions.initializeQueue()
        MedicationPetitions.initializeQueue()
        ManifestationPetitions.initializeQueue()
    }

    companion object{

        private lateinit var contextObj: Context
        private lateinit var binding : ActivityProfileBinding
        private lateinit var patient :Patient
        private lateinit var listManifestations: MutableList<Manifestation>

        fun getContext() : Context{
            return contextObj
        }

        fun updateListOfMedications(list : MutableList<Medication>){
            binding.medicamentsRecycler.adapter =  MedicationAdapter(list, contextObj,patient)
            binding.medicamentsRecycler.layoutManager = LinearLayoutManager(contextObj)
        }


        fun getListManifestations() : MutableList<Manifestation>{
            return listManifestations
        }

        fun updateListOfManifestations(list: MutableList<Manifestation>){
            listManifestations = list;
            binding.manifestRecycler.adapter = ManifestAdapter(contextObj,list);
            binding.manifestRecycler.layoutManager = LinearLayoutManager(contextObj);
        }

        fun updatePatienInf(patient: Patient){
            binding.patientAgeText.text=patient.age.toString()
            binding.patientNameText.text = "${patient.name} ${patient.surname}"
            binding.patientWeightText.text = patient.weight.toString() + " kg"
            binding.patientHeigthText.text = patient.height.toString() + " m"
        }
    }
}