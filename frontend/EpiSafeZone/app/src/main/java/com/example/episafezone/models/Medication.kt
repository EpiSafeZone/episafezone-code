package com.example.episafezone.models

import java.io.Serializable

class Medication(
    var name : String,
    var dosis : Int,
    var unit : String,
    var alarm : Boolean
):Serializable