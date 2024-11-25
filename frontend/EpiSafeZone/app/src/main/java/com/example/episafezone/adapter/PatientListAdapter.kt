package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.R
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.models.Patient

class PatientListAdapter(var context : Context, private var list : List<Patient>) : RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder>() {

    class PatientListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val patientName: TextView = view.findViewById(R.id.patientName)
        val patientImage: ImageView = view.findViewById(R.id.patientImage)
        val registerCrisisButton: Button = itemView.findViewById(R.id.registerCrisisButton)
        val startCrisisButton: Button = itemView.findViewById(R.id.startCrisisButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_patients, parent, false)
        val viewHold = PatientListViewHolder(view)
        return viewHold
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: PatientListViewHolder, position: Int) {
        holder.patientName.text = list[position].name
        holder.registerCrisisButton.setOnClickListener {
            ActivityPatientsList.startRegisterManifestation(list[position])
        }
        holder.startCrisisButton.setOnClickListener {
            ActivityPatientsList.loadStartCrisis(list[position])
        }
        //holder.patientImage.setImageIcon( list[position].profilePicture )

        holder.patientImage.setOnClickListener {
            ActivityPatientsList.startProfileActivity(list[position])
        }
    }
}