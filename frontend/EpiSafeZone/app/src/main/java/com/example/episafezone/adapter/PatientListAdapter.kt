package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.MainActivity
import com.example.episafezone.fragments.HomeFragment
import com.example.episafezone.R
import com.example.episafezone.models.Patient

class PatientListAdapter(var context: Context?, private var list: List<Patient>) : RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder>() {

    class PatientListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val patientContainer: ConstraintLayout = view.findViewById(R.id.container_profile_picture_circular)
        val patientImageContainer: ConstraintLayout = patientContainer.findViewById(R.id.patient_profile_picture)
        //TODO: llegar hasta la imageview de verdad.
        //val patientImage: ImageView = patientImageContainer.findViewById(R.id.patient_profile_picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_profile_picture_circular, parent, false)
        val viewHold = PatientListViewHolder(view)
        return viewHold
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: PatientListViewHolder, position: Int) {
        //TODO terminar imagen cuando funcione.
        //holder.patientImage.setImageIcon( list[position].profilePicture )

        patientListViews.add(holder.patientContainer)

        holder.patientContainer.setOnClickListener {
            MainActivity.updatePatient(list[position])
        }
    }

    companion object {

        var patientListViews = mutableListOf<ConstraintLayout>()

        fun ClearViewList(){
            patientListViews.clear()
        }

        fun setAllClickAble(){
            for (i in 0 until patientListViews.size){
                patientListViews[i].isClickable = true
            }
        }

        fun setAllNotClickAble(){
            for (i in 0 until patientListViews.size){
                patientListViews[i].isClickable = false
            }
        }
    }
}