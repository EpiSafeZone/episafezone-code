package com.example.episafezone.businesslogic

import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions
import java.util.Date

object PatientsListLogic {

    private var patient = Patient(1,"Cesar","Pardo","",1, Date(1678886400000L),1,"" );


    fun sendPetition(){
        PatientsListPetitions.getProfileInfo(patient);
    }
}