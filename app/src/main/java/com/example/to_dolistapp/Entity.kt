package com.example.to_dolistapp

import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var title :String,
    var priority:String
    )
