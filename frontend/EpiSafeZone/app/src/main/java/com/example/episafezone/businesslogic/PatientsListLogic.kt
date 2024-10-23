package com.example.episafezone.businesslogic

import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions
import java.util.Date

object PatientsListLogic {



    fun getProfileInfo(patient : Patient){
        PatientsListPetitions.getProfileInfo(patient);
    }
}