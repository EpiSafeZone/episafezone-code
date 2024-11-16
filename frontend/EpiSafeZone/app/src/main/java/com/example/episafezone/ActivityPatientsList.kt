package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions
import com.example.episafezone.network.StartCrisisPetitions
import java.util.Date

class ActivityPatientsList : AppCompatActivity() {

    private lateinit var binding: ActivityPatientsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contextObj = this
        binding = ActivityPatientsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPatient : List<Patient> = PatientsListLogic.getPatientListInfo();
        binding.PatientRecyclerView.adapter = PatientListAdapter(this, listPatient)
        binding.PatientRecyclerView.layoutManager = LinearLayoutManager(this)

        PatientsListPetitions.initializeQueue();
    }

    companion object{
        private lateinit var contextObj: Context
        fun getContext() : Context {
            return contextObj
        }

        fun startProfileActivity(patient: Patient){
            val intent = Intent(contextObj,ActivityProfile::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }

        fun loadStartCrisis(patient : Patient){
            val intent = Intent(contextObj,ActivityStartCrisis::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }
    }

}