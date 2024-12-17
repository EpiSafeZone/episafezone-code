package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R
import com.example.episafezone.models.Crisis

class CrisisAdapter(val context: Context,val list:MutableList<Crisis>): RecyclerView.Adapter<CrisisAdapter.CrisisViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrisisAdapter.CrisisViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_calendar_crisis, parent, false)
        val viewHold = CrisisViewHolder(view);
        return viewHold
    }

    override fun onBindViewHolder(holder: CrisisAdapter.CrisisViewHolder, position: Int) {
        holder.manifestation.text = list[position].manifestation
        holder.hour.text = list[position].hour
        holder.cont.text = list[position].context
    }

    override fun getItemCount(): Int {
        return list.count();
    }

    class CrisisViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val hour : TextView = itemView.findViewById(R.id.crisisHourText);
        val manifestation: TextView = itemView.findViewById(R.id.crisisManifText);
        val cont: TextView = itemView.findViewById(R.id.crisisContextManif);
    }
}