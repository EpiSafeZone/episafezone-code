package com.example.episafezone.models

import java.io.Serializable

class Medication(
    var id : Int,
    var name : String,
    var dosis : Int,
    var unit : String,
    var alarm : Boolean
):Serializable