package com.example.episafezone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.databinding.FragmentPatientListBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions

class PatientListFragment : Fragment(R.layout.fragment_patient_list) {

    private lateinit var binding: FragmentPatientListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPatientListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listPatient : List<Patient> = PatientsListLogic.getPatientListInfo();
        binding.PatientRecyclerView.adapter = PatientListAdapter(contextObj, listPatient)
        binding.PatientRecyclerView.layoutManager = LinearLayoutManager(contextObj)

        PatientsListPetitions.initializeQueue();
    }

    companion object {
        private val contextObj = MainActivity.getContext()

        fun startProfileActivity(patient: Patient){
            val intent = Intent(contextObj, ActivityProfile::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }

        fun loadStartCrisis(patient : Patient){
            val intent = Intent(contextObj, ActivityStartCrisis::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }
    }
}