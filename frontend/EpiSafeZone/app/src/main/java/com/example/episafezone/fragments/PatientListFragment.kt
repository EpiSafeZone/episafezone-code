package com.example.episafezone.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityPatientsList.Companion
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding
import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions

class PatientListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var binding:PatientListFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val listPatient : List<Patient> = PatientsListLogic.getPatientListInfo();
        binding.PatientRecyclerView.adapter = PatientListAdapter(context, listPatient)
        binding.PatientRecyclerView.layoutManager = LinearLayoutManager(context)

        PatientsListPetitions.initializeQueue();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = PatientListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        private val context = MainActivity.contextObj

        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PatientListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun startProfileActivity(patient: Patient){
            val intent = Intent(context, ActivityProfile::class.java)
            intent.putExtra("patient", patient)
            context.startActivity(intent)
        }

        fun loadStartCrisis(patient : Patient){
            val intent = Intent(context, ActivityStartCrisis::class.java)
            intent.putExtra("patient", patient)
            context.startActivity(intent)
        }
    }
}