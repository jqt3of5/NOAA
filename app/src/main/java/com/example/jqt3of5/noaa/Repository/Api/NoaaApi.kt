package com.example.jqt3of5.noaa.Repository.Api

import com.example.jqt3of5.noaa.Repository.Api.DataObjects.AlertCountsByLocation
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.WeatherServiceZone
import io.reactivex.Observable
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
    fun getAlertByZone(@Path("zone") zone : String) : Observable<WeatherServiceZone>


    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/area/{area}")
    fun getAlertByArea(@Path("area") area : String) : Observable<WeatherServiceZone>


    @Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    @GET("/alerts/active/count")
    fun getAlertCounts() : Observable<AlertCountsByLocation>

    //@Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    //@GET("/alerts/active/")
    //fun getAllActiveAlerts() : Observable<ZoneAlert>

    //@Headers("User-Agent: jqt3of5@gmail.com", "Accept: application/vnd.github.v3.full+json")
    //@GET("/alerts/active/region/{region}")
    //fun getAlertByRegion(@Path("region") region : String) : Observable<ZoneAlert>

}