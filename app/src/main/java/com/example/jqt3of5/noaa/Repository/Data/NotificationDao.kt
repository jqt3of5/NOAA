package com.example.jqt3of5.noaa.Repository.Data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification

@Dao
interface NotificationDao {

    @Query("SELECT * FROM " + Notification.TABLE_NAME)
    fun getAllNotifications() : LiveData<List<Notification>>

    @Insert(onConflict = REPLACE)
    fun insert(notification : Notification)
}