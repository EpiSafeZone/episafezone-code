package com.example.episafezone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.episafezone.models.Manifestation

class ManifestationSpinnerAdapter(context: Context, manifestations: List<Manifestation>) :
    ArrayAdapter<Manifestation>(context, 0, manifestations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        val manifestation = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = manifestation?.name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val manifestation = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = manifestation?.name
        return view
    }
}