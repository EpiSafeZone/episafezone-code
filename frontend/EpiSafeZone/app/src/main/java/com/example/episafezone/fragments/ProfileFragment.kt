package com.example.episafezone.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityAddMedication
import com.example.episafezone.ActivityRegisterManifestation
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.adapter.SharedAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.FragmentPatientListBinding
import com.example.episafezone.databinding.FragmentProfileBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Patient
import com.example.episafezone.models.SharedUser
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.MedicationPetitions
import com.example.episafezone.network.PatientsListPetitions
import com.example.episafezone.network.ProfilePetitions

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProfilePetitions.initializeQueue()
        MedicationPetitions.initializeQueue()
        ManifestationPetitions.initializeQueue()

        ProfilePetitions.getProfileInfo(patient)

        binding.addMedButt.setOnClickListener(){
            val intent = Intent(contextObj, ActivityAddMedication::class.java)
            intent.putExtra("patient", patient)
            startActivity(intent)
        }

        binding.addManifButt.setOnClickListener{
            val intent = Intent(contextObj, ActivityRegisterManifestation::class.java)
            intent.putExtra("patient", patient)
            startActivity(intent)
        }
    }

    companion object {
        private lateinit var binding: FragmentProfileBinding
        private lateinit var listManifestations: MutableList<Manifestation>

        private val contextObj = MainActivity.getContext()
        private var patient = MainActivity.getPatient()

        fun startProfile(json : String){
            ProfileLogic.setUpInfo(json)
        }

        fun getContext() : Context {
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

        fun updateListOfShared(list: MutableList<SharedUser>){
            binding.sharedRecycler.adapter = SharedAdapter(contextObj,list);
            binding.sharedRecycler.layoutManager = LinearLayoutManager(contextObj);
        }

        fun updatePatienInf(patient: Patient){
            binding.patientAgeText.text=patient.age.toString()
            binding.patientNameText.text = "${patient.name} ${patient.surname}"
            binding.patientWeightText.text = patient.weight.toString() + " kg"
            binding.patientHeigthText.text = patient.height.toString() + " m"
        }

        fun updatePatient(patient: Patient) {
            this.patient = patient
        }
    }
}