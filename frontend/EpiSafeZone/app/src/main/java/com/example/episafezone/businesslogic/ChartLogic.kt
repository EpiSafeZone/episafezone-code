package com.example.episafezone.businesslogic

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.episafezone.R
import com.example.episafezone.databinding.FragmentChartsBinding
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.Patient

object ChartLogic {
    fun getPieChart(patient: Patient) : List<String>{
        val list = mutableListOf<String>();
        list.add("hola")
        list.add("tardes")
        list.add("tardes")
        list.add("hola")
        list.add("buenas")
        list.add("hola")
        list.add("hola1")
        return list;
    }

    fun getLineChart(patient: Patient) : Map<String,Int>{
        val dateNumbers = mapOf(
            "12/01" to 3,
            "12/02" to 0,
            "12/03" to 2,
            "12/04" to 3,
            "12/05" to 1,
            "12/06" to 2,
            "12/07" to 1
        )
        return dateNumbers;
    }

    fun decorateCharts(binding: FragmentChartsBinding){
        val epiWhite = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiWhite)
        val epiGreen = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiGreen)
        val epiBack = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiBlackBackground)



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