package com.example.episafezone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.ActivityRegisterManifestation
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.databinding.FragmentHomeBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        patient = MainActivity.getPatient()

        PatientsListPetitions.initializeQueue();
    }

    companion object {
        private lateinit var binding: FragmentHomeBinding
        private lateinit var patient : Patient

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