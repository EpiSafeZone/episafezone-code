package com.example.episafezone.businesslogic

import android.content.Intent
import android.widget.Toast
import com.example.episafezone.ActivityRegisterCrisis
import com.example.episafezone.MainActivity
import com.example.episafezone.models.Crisis
import com.example.episafezone.network.StartCrisisPetitions

object StartCrisisLogic {

    fun SuccessfullRegisterCrisis() {
        val intent = Intent(ActivityRegisterCrisis.getContext(), MainActivity::class.java)
        intent.putExtra("load", MainActivity.CALENDAR_VIEW)
        ActivityRegisterCrisis.getContext().startActivity(intent)
    }

    fun FailedRegisterCrisis() {
        Toast.makeText(ActivityRegisterCrisis.getContext(), "Error al registrar la crisis", Toast.LENGTH_LONG).show()
    }

    fun registerCrisis(crisis: Crisis) {
        StartCrisisPetitions.registerCrisis(crisis)
    }
}