package com.example.mnprecords.DataLayer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Recode")
data class MNPRecode(
    val Data:String,
    val jio:Int,
    val VI:Int,
    val Airtel:Int,
    val UIBalance:Int,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
)
