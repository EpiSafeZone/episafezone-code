package com.example.episafezone.businesslogic

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
        return list;
    }

    fun getLineChart(patient: Patient) : Map<String,Int>{
        val dateNumbers = mapOf(
            "2023-12-01" to 10,
            "2023-12-02" to 15,
            "2023-12-03" to 8,
            "2023-12-04" to 20,
            "2023-12-05" to 12
        )
        return dateNumbers;
    }
}