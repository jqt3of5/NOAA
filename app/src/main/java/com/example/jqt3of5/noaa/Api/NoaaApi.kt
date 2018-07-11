package com.example.jqt3of5.noaa.Api

import com.example.jqt3of5.noaa.Api.DataObjects.AlertCountsByLocation
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert
import com.example.jqt3of5.noaa.Api.DataObjects.ZoneAlert
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

//https://www.ncdc.noaa.gov/cdo-web/api/v2/{endpoint}

interface NoaaApi
{
}

//https://api.weather.gov/
interface WeatherApi
{
    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/zone/{zone}")
    fun getAlertByZone(@Path("zone") zone : String) : Observable<AreaAlert>

    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/region/{region}")
    fun getAlertByRegion(@Path("region") region : String) : Observable<ZoneAlert>

    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/area/{area}")
    fun getAlertByArea(@Path("area") area : String) : Observable<AreaAlert>

    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/")
    fun getAllActiveAlerts() : Observable<ZoneAlert>

    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/count")
    fun getAlertCounts() : Observable<AlertCountsByLocation>
}