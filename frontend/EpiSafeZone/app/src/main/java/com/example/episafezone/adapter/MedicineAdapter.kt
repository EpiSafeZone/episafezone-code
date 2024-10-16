package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R

class MedicineAdapter(var list : List<String>,var context: Context) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>()   {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MedicineViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_medicament, parent, false)
        val viewHold = MedicineViewHolder(view);
        return viewHold
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.text.text = list[position];
    }

    override fun getItemCount(): Int {
        return list.count();
    }

    class MedicineViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val text : TextView = itemView.findViewById(R.id.medicineName);

    }
}



