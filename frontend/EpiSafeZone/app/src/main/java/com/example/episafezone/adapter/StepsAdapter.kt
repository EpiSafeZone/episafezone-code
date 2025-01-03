package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R

class StepsAdapter(private val context: Context, private val steps: List<String>) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_display_steps, parent, false)
        return StepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.stepTextView.text = steps[position]
    }

    override fun getItemCount(): Int = steps.size

    class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepTextView: TextView = itemView.findViewById(R.id.stepsId)
    }
}
