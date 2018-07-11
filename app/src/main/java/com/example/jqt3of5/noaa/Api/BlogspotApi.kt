package com.example.jqt3of5.noaa.Api


import com.example.jqt3of5.noaa.Api.DataObjects.BlogPosts
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//https://www.googleapis.com/
interface BlogspotApi {

    @GET("/blogger/v3/{id}/posts")
    fun getBlogPosts(@Path("id") id : Int, @Query("apikey") key : String) : Observable<BlogPosts>

}