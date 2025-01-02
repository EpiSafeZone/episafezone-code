package com.example.episafezone.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.ActivityAddMedication
import com.example.episafezone.ActivityEditMedication
import com.example.episafezone.R
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Patient

class MedicationAdapter(var list : List<Medication>, var context: Context,var patient: Patient) : RecyclerView.Adapter<MedicationAdapter.MedicineViewHolder>()   {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MedicineViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_medicament, parent, false)
        val viewHold = MedicineViewHolder(view);
        return viewHold
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        if(position == 0){
            holder.name.text = "Añadir nuevo medicamento"
            holder.button.setOnClickListener(){
                val intent = Intent(context, ActivityAddMedication::class.java)
                intent.putExtra("patient", ProfileFragment.patient)
                context.startActivity(intent)
            }
        }
        else {
            val item = list[position - 1]
            holder.name.text = item.name.replaceFirstChar { it.uppercase() }
            holder.desc.text = "${item.dosis} ${item.unit}"
            holder.button.setOnClickListener() {
                val intent = Intent(context, ActivityEditMedication::class.java)
                intent.putExtra("medication", item)
                intent.putExtra("patient", patient)
                context.startActivity(intent);
            }
        }
    }

    override fun getItemCount(): Int {
        return list.count() + 1 // +1 porque el primer elemento es constante
    }

    class MedicineViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.medicineName);
        val desc : TextView = itemView.findViewById(R.id.medicationDesc)
        val button : View = itemView.findViewById(R.id.editMedicineButt)
    }
}