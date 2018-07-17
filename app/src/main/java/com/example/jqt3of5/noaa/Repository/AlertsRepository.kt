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

    var alertsData : LiveData<List<WeatherAlert>>? = null

    fun startGetForAlerts(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        downloadAlertForZone(zoneCode)
        val db = MainDatabase.getInstance()
        alertsData = db.weatherAlerts().selectByZoneCode(zoneCode)
        return alertsData!!
    }

    fun downloadAlertForZone(zoneCode : String) : LiveData<List<WeatherAlert>>
    {
        val liveData = MutableLiveData<List<WeatherAlert>>()

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
                                    MainDatabase.DatabaseAsync().execute {
                                        weatherAlerts().insertAll(it)
                                        liveData.postValue(it)
                                    }
                                }
                            }
                        }
                    })
        }

        return liveData
    }


}