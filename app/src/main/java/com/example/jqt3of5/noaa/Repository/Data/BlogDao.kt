package com.example.jqt3of5.noaa.Repository.Data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.jqt3of5.noaa.Repository.Data.Entities.BlogPost
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

@Dao
interface BlogDao {
    @Query("SELECT * FROM " + BlogPost.TABLE_NAME)
    fun getAllPosts() : LiveData<List<BlogPost>>

    @Query("SELECT * FROM " + BlogPost.TABLE_NAME + " WHERE id = :id")
    fun selectById(id : Long) : BlogPost?

    @Insert
    fun insert(post : BlogPost)

    @Insert
    fun insertAll(post : List<BlogPost>)
}