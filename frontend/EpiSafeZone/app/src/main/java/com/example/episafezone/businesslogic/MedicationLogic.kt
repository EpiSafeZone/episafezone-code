package com.example.episafezone.businesslogic

import com.example.episafezone.models.Medication
import com.example.episafezone.models.Reminder

class MedicationLogic {

    fun createMedication(name : String, dosis : String, unit : String, alarm:Boolean, times : String?, nextAlarm: String?):Boolean{
        if(name == ""){throw Exception("Debes rellenar el nombre")}
        if(unit == ""){throw Exception("Debes rellenar la unidad")}
        if(dosis == ""){throw Exception("Debes rellenar la dosis")}
        if(alarm){
            if(nextAlarm == ""){throw Exception("Debes rellenar las horas")}
            if(times == ""){throw Exception("Debes rellenar la cantidad de tomas")}
            Reminder(times!!.toInt(), nextAlarm!!);
        }
        Medication(name,dosis.toInt(),unit,alarm);
        return true;
    }
}