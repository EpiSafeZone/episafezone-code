package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.models.Patient

class PatientListAdapter(var context : Context, private var list : List<Patient>) : RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder>() {
    private var onClickListener: OnClickListener? = null
    class PatientListViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val patientName : TextView = view.findViewById(R.id.patientName)
        val registerCrisisButton : Button = itemView.findViewById(R.id.registerCrisisButton)
        val startCrisisButton : Button = itemView.findViewById(R.id.startCrisisButton)
        //val patientProfilePicture : ImageView = view.findViewById(R.id.patientImage)
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

        }
        holder.startCrisisButton.setOnClickListener {

        }
        //holder.patientProfilePicture.setImageIcon( list[position].profilePicture )

        holder.patientName.setOnClickListener {
            PatientsListLogic.getProfileInfo(list[position])
        }
    }
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Patient)
    }

}