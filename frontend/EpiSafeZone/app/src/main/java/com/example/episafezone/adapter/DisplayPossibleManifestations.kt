package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R
import com.example.episafezone.models.Manifestation

class DisplayPossibleManifestations(
    private val context: Context,
    private val list: List<Manifestation>,
    private val onManifestationClick: (Manifestation) -> Unit
) : RecyclerView.Adapter<DisplayPossibleManifestations.DisplayPosibleManifestationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayPosibleManifestationsViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_display_possible_manifestation, parent, false)
        return DisplayPosibleManifestationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisplayPosibleManifestationsViewHolder, position: Int) {
        val manifestation = list[position]
        holder.name.text = manifestation.name
        holder.description.text = manifestation.description

        holder.manifestationContainer.setOnClickListener {
            onManifestationClick(manifestation)
        }
    }

    override fun getItemCount(): Int = list.size

    class DisplayPosibleManifestationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.manifestName)
        val description: TextView = itemView.findViewById(R.id.manifestDescription)
        val manifestationContainer: ConstraintLayout = itemView.findViewById(R.id.manifestationContainer)
    }
}
