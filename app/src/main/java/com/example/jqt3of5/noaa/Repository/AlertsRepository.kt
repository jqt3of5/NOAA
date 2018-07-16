package com.example.jqt3of5.noaa.Repository

import android.arch.lifecycle.LiveData
import com.example.jqt3of5.noaa.Repository.Api.NetworkingFactory
import com.example.jqt3of5.noaa.Repository.Api.WeatherApi
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import com.example.jqt3of5.noaa.Repository.Data.MainDatabase
import io.reactivex.schedulers.Schedulers
import java.time.Instant
import java.util.*

class AlertsRepository {

    fun getAlertForZone(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        val db = MainDatabase.getInstance()

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
                    if (it.any())
                    {
                        db.notifications().insert(Notification(WeatherAlert.TABLE_NAME, it.first().id, Date()))
                        db.weatherAlerts().insertAll(it)
                    }
                }
            }
        }

        return db.weatherAlerts().selectByZoneCode(zoneCode)
    }
}