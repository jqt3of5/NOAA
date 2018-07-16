package com.example.jqt3of5.noaa.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.jqt3of5.noaa.Repository.Api.NetworkingFactory
import com.example.jqt3of5.noaa.Repository.Api.WeatherApi
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import com.example.jqt3of5.noaa.Repository.Data.MainDatabase
import io.reactivex.schedulers.Schedulers
import java.time.Instant
import java.util.*

class AlertsRepository {
    fun updateAlertsForZones(zones : List<String>)
    {

    }
    fun getAlertForZone(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        val db = MainDatabase.getInstance()

        val liveData = db.weatherAlerts().selectByZoneCode(zoneCode)

        updateAlertForZone(zoneCode)

        return
    }

    fun updateAlertForZone(zoneCode :String) : LiveData<List<WeatherAlert>>
    {
        val db = MainDatabase.getInstance()

        val liveData = MutableLiveData<List<WeatherAlert>>()

        NetworkingFactory.api<WeatherApi>
        {
            getAlertByZone(zoneCode)
                .subscribeOn(Schedulers.io())
                .doOnError {

                }
                .subscribe { zone ->

                    zone.features?.map{ feature ->

                        feature.properties?.let {
                            WeatherAlert(feature.id, zoneCode, it.areaDesc, it.headline, it.description, it.severity, it.certainty, it.event, it.instruction, it.sent, it.effective, it.expires, it.ends)
                        }

                    }?.filterNotNull()?.let {
                        db.weatherAlerts().insertAll(it)
                    }
                }
        }

        return liveData
    }
}