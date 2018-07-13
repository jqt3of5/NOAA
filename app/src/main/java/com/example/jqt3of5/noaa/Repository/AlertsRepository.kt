package com.example.jqt3of5.noaa.Repository

import android.arch.lifecycle.LiveData
import com.example.jqt3of5.noaa.Repository.Api.NetworkingFactory
import com.example.jqt3of5.noaa.Repository.Api.WeatherApi
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import com.example.jqt3of5.noaa.Repository.Data.MainDatabase

class AlertsRepository {

    fun getAlertForZone(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        val zones = MainDatabase.mInstance.weatherAlerts().selectByZoneCode(zoneCode)

        NetworkingFactory.api<WeatherApi>
        {
                getAlertByZone(zoneCode).doOnNext { zone ->
                    zone.features?.firstOrNull()?.let { feature ->
                        feature.properties?. let {
                            val alert = WeatherAlert(feature.id, zoneCode, it.properties?.areaDesc ?: "", )
                            MainDatabase.mInstance.weatherAlerts().insert()
                        }

                }
            }
        }
    }
}