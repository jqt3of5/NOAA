package com.example.jqt3of5.noaa.Repository.Data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

@Database(entities = [Notification::class, WeatherAlert::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    companion object {

        lateinit var mInstance : MainDatabase

        fun getInstance(context : Context) : MainDatabase
        {
            mInstance?. let {
                return it
            }

            mInstance = Room.databaseBuilder(context.applicationContext, MainDatabase::class.java, "main").build()
            return mInstance
        }
    }

    abstract fun notifications() : NotificationDao
    abstract fun weatherAlerts() : WeatherAlertDao
}