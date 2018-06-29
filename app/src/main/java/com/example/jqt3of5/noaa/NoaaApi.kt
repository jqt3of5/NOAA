package com.example.jqt3of5.noaa

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

//https://www.ncdc.noaa.gov/cdo-web/api/v2/{endpoint}
//https://api.weather.gov/
interface NoaaApi
{
}

interface WeatherApi
{
    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/zone/{zone}")
    fun getAlertByZone(@Path("zone") zone : String) : Call<ZoneAlert>

    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/")
    fun getAllActiveAlerts() : Call<ZoneAlert>
}