package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.R

class ManifestAdapter(var context : Context, var list : List<String>) : RecyclerView.Adapter<ManifestAdapter.ManifestViewHolder>(){
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
        holder.text.text=list[position];
        holder.but
    }

    class ManifestViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val text : TextView = itemView.findViewById(R.id.manifestName);
        val but : Button = itemView.findViewById(R.id.addManifButt);
    }


}