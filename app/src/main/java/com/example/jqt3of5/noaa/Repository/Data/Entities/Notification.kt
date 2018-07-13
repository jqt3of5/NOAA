package com.example.jqt3of5.noaa.Repository.Data.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = Notification.TABLE_NAME)
class Notification {
    companion object {
        const val TABLE_NAME : String = "Notifications"
    }

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

    var table : String = ""

    var foreign_key : String = ""

    var date : Date? = null

}