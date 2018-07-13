package com.example.jqt3of5.noaa.Repository.Data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

@Dao
interface WeatherAlertDao {

    @Query("SELECT * FROM " + WeatherAlert.TABLE_NAME + "WHERE zoneCode = :zone")
    fun selectByZoneCode(zone : String) : LiveData<List<WeatherAlert>>

    @Insert
    fun insert(alert : WeatherAlert) : Long



}