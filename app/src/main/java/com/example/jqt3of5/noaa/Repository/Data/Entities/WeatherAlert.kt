package com.example.jqt3of5.noaa.Repository.Data.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = WeatherAlert.TABLE_NAME)
class WeatherAlert(@PrimaryKey(autoGenerate = false)
                   var id : String,
                   var zoneCode: String,
                   var areaDesc : String,
                   var headline : String,
                   var description : String,
                   var severity : String,
                   var certainty : String,
                   var event : String,
                   var instruction: String,
                   var sent : Date?,
                   var effective: Date?,
                   var expires : Date?,
                   var ends : Date?)
{
    companion object {
        const val TABLE_NAME : String = "WeatherAlerts"
    }
}