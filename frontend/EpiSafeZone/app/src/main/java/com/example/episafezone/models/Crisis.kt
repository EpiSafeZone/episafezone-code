package com.example.episafezone.models

import java.util.Date

class Crisis (
    val id : Int,
    val duration : Int,
    val date : Date,
    val hour : String,
    val context : String,
    val emergency : Boolean,
    val manifestationName : String,
    val manifestationId : Int,
    val patient : Int) {

    constructor(duration: Int,
                date: Date,
                hour: String,
                context: String,
                emergency: Boolean,
                manifestationName: String,
                manifestationId: Int,
                patient: Int) : this(
        duration = duration,
        date = date,
        hour = hour,
        context = context,
        emergency = emergency,
        manifestationName = manifestationName,
        manifestationId = manifestationId,
        patient = patient,
        id = -1

    )
}