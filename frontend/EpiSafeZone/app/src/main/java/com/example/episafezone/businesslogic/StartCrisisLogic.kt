package com.example.episafezone.businesslogic

import android.widget.Toast
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.models.Crisis
import com.example.episafezone.network.StartCrisisPetitions

object StartCrisisLogic {

    fun SuccessfullRegisterCrisis() {
        Toast.makeText(ActivityRegisterCrisis.getContext(), "Crisis registrada", Toast.LENGTH_LONG).show()
    }

    fun FailedRegisterCrisis() {
        Toast.makeText(ActivityRegisterCrisis.getContext(), "Error al registrar la crisis", Toast.LENGTH_LONG).show()
    }

    fun registerCrisis(crisis: Crisis) {
        StartCrisisPetitions.registerCrisis(crisis)
    }
}