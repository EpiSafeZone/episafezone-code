package com.example.episafezone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.ActivityRegisterManifestation
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.databinding.FragmentPatientListBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions

class HomeFragment : Fragment(R.layout.fragment_patient_list) {

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

        PatientsListPetitions.initializeQueue();
    }

    companion object {
        private lateinit var binding: FragmentPatientListBinding

        private val contextObj = MainActivity.getContext()

        fun loadStartCrisis(){
            MainActivity.changeToStartCrisis()
        }

        fun startRegisterManifestation(){
            val intent = Intent(contextObj, ActivityRegisterManifestation::class.java)
            contextObj.startActivity(intent)
        }

        fun startRegisterCrisis(patient: Patient){
            val intent = Intent(contextObj, ActivityRegisterCrisis::class.java)
            contextObj.startActivity(intent)
        }
    }
}