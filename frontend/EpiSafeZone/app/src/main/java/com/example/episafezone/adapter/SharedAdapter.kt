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
import com.example.episafezone.models.SharedUser


class SharedAdapter(var context : Context, var list : List<SharedUser>) : RecyclerView.Adapter<SharedAdapter.SharedViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.container_shared, parent, false)
            val viewHold = SharedViewHolder(view);
            return viewHold
        }

        override fun getItemCount(): Int {
            return list.count();
        }

        override fun onBindViewHolder(holder: SharedViewHolder, position: Int) {
            holder.name.text=list[position].name + " " + list[position].surname;

        }

        class SharedViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
            val name : TextView = itemView.findViewById(R.id.userName);
        }

        companion object {
            private lateinit var currentManifestation: Manifestation
        }
}
