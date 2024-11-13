package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R
import com.example.episafezone.models.Manifestation

class DisplayPossibleManifestations (var context : Context, var list : List<Manifestation>) : RecyclerView.Adapter<DisplayPossibleManifestations.DisplayPosibleManifestationsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayPosibleManifestationsViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_display_possible_manifestation, parent, false)
        val viewHold = DisplayPosibleManifestationsViewHolder(view);
        return viewHold
    }

    override fun getItemCount(): Int {
        return list.count();
    }

    override fun onBindViewHolder(holder: DisplayPosibleManifestationsViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.description.text = list[position].description
        holder.manifestationContainer.setOnClickListener {
            //TODO: Implementar la lógica para cargar los pasos a seguir
            Toast.makeText(context, "Se ha seleccionado una manifestacion, esta funcionalidad no está implementada aún.", Toast.LENGTH_SHORT).show()
        }
    }

    class DisplayPosibleManifestationsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.manifestName)
        val description : TextView = itemView.findViewById<TextView>(R.id.manifestDescription)
        val manifestationContainer : ConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.manifestationContainer)
    }
}