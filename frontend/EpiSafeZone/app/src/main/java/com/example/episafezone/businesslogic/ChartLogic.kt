package com.example.episafezone.businesslogic

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.episafezone.R
import com.example.episafezone.databinding.FragmentChartsBinding
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.LineChartAdapter
import com.example.episafezone.models.Patient
import com.example.episafezone.models.PieChartAdapter
import com.example.episafezone.network.ChartPetitions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

object ChartLogic {
    fun getPieChart(patient: Patient) {
        ChartPetitions.getNumberOfManifestations(patient)
    }

    fun collectDataPieChart(json : JSONObject) {
        val list = mutableListOf<String>()
        val gson = Gson()
        val jsonArray = json.get("numPerManifestation") as JSONArray
        val listType = object : TypeToken<MutableList<PieChartAdapter>>() {}.type
        val manifestations : MutableList<PieChartAdapter> = gson.fromJson(jsonArray.toString(),listType)
        manifestations.forEach{manif ->
            var i = 0
            while(i<manif.num){
                list.add(manif.name)
                i++
            }
        }
        ChartFragment.setUpPieChart(list)
    }

    fun getLineChart(patient: Patient){
        ChartPetitions.getNumberOfCrisis(patient)
    }

    fun collectDataLineChart(json : JSONObject) {
        val map = HashMap<String,Int>()
        val gson = Gson()
        val jsonArray = json.get("lista") as JSONArray
        val listType = object : TypeToken<MutableList<LineChartAdapter>>() {}.type
        val correspondence : MutableList<LineChartAdapter> = gson.fromJson(jsonArray.toString(),listType)

        correspondence.forEach{group->
            val string = "${group.dia.month+1}/${group.dia.date}"
            map[string]=group.numCrisis
        }

        ChartFragment.setUpLineChart(map)
    }

    fun decorateCharts(binding: FragmentChartsBinding){
        val epiWhite = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiWhite)

        val pie = binding.pieChart
        pie.legend.textColor = epiWhite
        pie.legend.textSize = 14f
        pie.centerText = ""
        pie.setHoleColor(Color.TRANSPARENT)
        pie.holeRadius = 30f
        pie.setTransparentCircleColor(Color.TRANSPARENT)
        pie.transparentCircleRadius = 0f

        val line = binding.lineChart
        line.axisRight.isEnabled = false
        line.xAxis.textColor = epiWhite
        line.xAxis.textSize = 12f
        line.axisLeft.textColor = epiWhite
        line.axisLeft.textSize = 15f
        line.axisLeft.granularity = 1f
        line.axisLeft.isGranularityEnabled = true
        line.axisLeft.axisMinimum = 0f
        line.axisLeft.axisMaximum = 5f
        line.legend.textColor = epiWhite
        line.legend.textSize = 15f
        line.legend.isEnabled = false
        line.axisRight.isEnabled = false
    }
}