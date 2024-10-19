package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R
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
        holder.text.text=list[position].name;
    }

    class ManifestViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val text : TextView = itemView.findViewById(R.id.manifestName);
    }
}