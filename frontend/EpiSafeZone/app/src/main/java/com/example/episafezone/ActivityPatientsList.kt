package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions
import java.util.Date

class ActivityPatientsList : AppCompatActivity() {

    private var patientGot = Patient(1,"Cesar","Pardo",180,1, Date(1678886400000L),1,"" );
    private lateinit var binding: ActivityPatientsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contextObj = this
        patient = patientGot
        binding = ActivityPatientsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPatient : List<Patient> = PatientsListLogic.getPatientListInfo();
        binding.PatientRecyclerView.adapter = PatientListAdapter(this, listPatient)
        binding.PatientRecyclerView.layoutManager = LinearLayoutManager(this)

        PatientsListPetitions.initializeQueue();
    }

    companion object{
        private lateinit var contextObj: Context
        private lateinit var patient : Patient
        fun getContext() : Context {
            return contextObj
        }

        fun startProfile(returns : String){
            val intent = Intent(contextObj,ActivityProfile::class.java)
            intent.putExtra("json", returns)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }
    }

}