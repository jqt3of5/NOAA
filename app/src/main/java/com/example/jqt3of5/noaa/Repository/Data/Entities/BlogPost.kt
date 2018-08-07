package com.example.jqt3of5.noaa.Repository.Data.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = BlogPost.TABLE_NAME)
class BlogPost(
        val title : String,
        val description : String,
        val author : String,
        val imageUrl : String?
) : INotifiableEntity {
    companion object {
        const val TABLE_NAME : String = "BlogPosts"
    }

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}