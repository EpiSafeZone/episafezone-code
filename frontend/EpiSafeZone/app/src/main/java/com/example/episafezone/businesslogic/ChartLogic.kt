package com.example.episafezone.businesslogic

import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.Patient
import com.example.episafezone.network.ChartPetitions
import org.json.JSONObject

object ChartLogic {
    fun getPieChart(patient: Patient){
        ChartPetitions.getManifestationList(patient);
    }

    fun processPieResponse(): List<String>{
        val pieList = mutableListOf<String>()
        pieList.add("tonico-clonico")
        pieList.add("tonico-clonico")
        pieList.add("tonico-clonico")
        pieList.add("tonico-clonico")
        pieList.add("ausencias")
        pieList.add("ausencias")
        pieList.add("ausencias")
        pieList.add("ausencias")
        pieList.add("ausencias")
        return pieList;
    }

    fun getLineChart(patient: Patient) : Map<String,Int>{
        val dateNumbers = mapOf(
            "2024-12-06" to 2,
            "2024-12-07" to 1,
            "2024-12-08" to 3,
            "2024-12-09" to 0,
            "2024-12-10" to 0,
            "2024-12-11" to 2
        )
        return dateNumbers;
    }
}