package com.example.episafezone.models

import java.io.Serializable
import java.util.Date

class Patient(
    var id : Int,
    var name : String,
    var surname : String,
    var height : Int,
    var weight: Int,
    val age: Int?,
) : Serializable