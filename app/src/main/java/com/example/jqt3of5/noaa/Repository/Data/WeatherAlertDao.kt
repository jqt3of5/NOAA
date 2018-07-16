package com.example.jqt3of5.noaa.Repository.Data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import io.reactivex.Single

@Dao
interface WeatherAlertDao {

    @Query("SELECT * FROM " + WeatherAlert.TABLE_NAME + " WHERE zoneCode = :zone")
    fun selectByZoneCode(zone : String) : LiveData<List<WeatherAlert>>

    @Query("SELECT * FROM " + WeatherAlert.TABLE_NAME + " WHERE id = :id")
    fun selectById(id : String) : Single<WeatherAlert?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(alert : WeatherAlert)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(alerts : List<WeatherAlert>)

}