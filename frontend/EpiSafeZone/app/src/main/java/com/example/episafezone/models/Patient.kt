package com.example.episafezone.models

import java.io.Serializable
import java.util.Date

class Patient(
    var id : Int,
    var name : String,
    var surname : String,
    var heigth : Int,
    var weight: Int,
    val birthdate: Date?,
    val age: Int?,
    val color: String?,
) : Serializable