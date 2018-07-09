package com.example.jqt3of5.noaa.Api


import com.example.jqt3of5.noaa.Api.DataObjects.BlogPosts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//https://www.googleapis.com/
interface BlogspotApi {
    @GET("/blogger/v3/{id}/posts?apikey=23412341234")
    fun getBlogPosts(@Path("id") id : Int) : BlogPosts
}