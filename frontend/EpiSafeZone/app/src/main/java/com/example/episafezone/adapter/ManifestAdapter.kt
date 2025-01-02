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
import com.example.episafezone.businesslogic.ManifestationLogic
import com.example.episafezone.models.Manifestation

class ManifestAdapter(var context : Context, var list : List<Manifestation>) : RecyclerView.Adapter<ManifestAdapter.ManifestViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManifestViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.container_manifest, parent, false)
        val viewHold = ManifestViewHolder(view);
        return viewHold
    }

    override fun getItemCount(): Int {
        return list.count();
    }

    override fun onBindViewHolder(holder: ManifestAdapter.ManifestViewHolder, position: Int) {
        holder.name.text=list[position].name;
        holder.editManifestationButton.setOnClickListener{
            currentManifestation = list[position]
            ManifestationLogic.loadEditManifestation(context, list[position])
        }
    }

    class ManifestViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.manifestName);
        val editManifestationButton: Button = itemView.findViewById(R.id.editManifestButt);
    }

    companion object {
        private lateinit var currentManifestation: Manifestation

        fun getCurrentManifestation(): Manifestation {
            return currentManifestation
        }
    }
}