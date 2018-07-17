package com.example.jqt3of5.noaa.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.WeatherServiceZone
import com.example.jqt3of5.noaa.Repository.Api.NetworkingFactory
import com.example.jqt3of5.noaa.Repository.Api.WeatherApi
import com.example.jqt3of5.noaa.Repository.Data.Entities.Notification
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import com.example.jqt3of5.noaa.Repository.Data.MainDatabase
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.util.*

class AlertsRepository {

    fun updateAlertForZone(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        getAlertForZone(zoneCode)
        val db = MainDatabase.getInstance()
        return db.weatherAlerts().selectByZoneCode(zoneCode)
    }

    fun updateAlertsForZones(zones : List<String>)
    {
        zones.forEach {
            updateAlertForZone(it)
        }
    }

    fun getAlertForZone(zoneCode : String)
    {
        val liveData = MutableLiveData<List<WeatherAlert>>()

        val db = MainDatabase.getInstance()
        NetworkingFactory.api<WeatherApi>
        {
            getAlertByZone(zoneCode)
                    .enqueue(object : Callback<WeatherServiceZone> {
                        override fun onFailure(call: Call<WeatherServiceZone>?, t: Throwable?) {
                        }

                        override fun onResponse(call: Call<WeatherServiceZone>?, response: Response<WeatherServiceZone>?) {
                            response?.body()?.let {
                                it.features?.map{ feature ->
                                    feature.properties!!.let {
                                        WeatherAlert(feature.id, zoneCode, it.areaDesc, it.headline, it.description, it.severity, it.certainty, it.event, it.instruction, it.sent, it.effective, it.expires, it.ends)
                                    }
                                }?.let {
                                    db.weatherAlerts().insertAll(it)
                                }
                            }
                        }
                    })
        }
    }
}