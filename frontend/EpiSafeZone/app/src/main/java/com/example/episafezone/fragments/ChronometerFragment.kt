package com.example.episafezone.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.DisplayPossibleManifestations
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.ChronometerLogic
import com.example.episafezone.databinding.FragmentChronometerBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.StartCrisisPetitions

class ChronometerFragment(val startChrono: Boolean) : Fragment(R.layout.fragment_chronometer) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChronometerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chronometer = binding.chrono
        progressBar = binding.progressBar

        patient = MainActivity.getPatient()

        StartCrisisPetitions.initializeQueue();

        ChronometerLogic.getProfileLogic(patient)

        if(startChrono){
            //binding.button.text = "Detener"
            //binding.button.setBackgroundColor(getColor(ChronometerFragment.getContext(), R.color.red))
            ChronometerLogic.startStopTimer(binding,patient)
        }

        binding.button.setOnClickListener {
            ChronometerLogic.startStopTimer(binding,patient)
        }
    }

    companion object {
        lateinit var chronometer: Chronometer
        lateinit var progressBar: ProgressBar

        private lateinit var binding: FragmentChronometerBinding
        private lateinit var manifestations: MutableList<Manifestation>

        var time = 0.0

        private val contextObj = MainActivity.getContext()
        private var patient = MainActivity.getPatient()

        fun startProfile(json : String){
            ProfileLogic.setUpInfo(json)
        }

        fun getContext() : Context {
            return contextObj
        }

        fun getBinding() : FragmentChronometerBinding {
            return binding
        }

        fun updatePosibleManifestations(list: MutableList<Manifestation>) {
            manifestations = list
            binding.informationScrollView.adapter = DisplayPossibleManifestations(contextObj, list)
            binding.informationScrollView.layoutManager = LinearLayoutManager(contextObj)
        }

        fun startCrisisRegister(chrono : Long){
            val intent = Intent(contextObj, ActivityRegisterCrisis::class.java)
            intent.putExtra("chrono",true)
            intent.putExtra("time", chrono)
            contextObj.startActivity(intent)
        }
    }
}