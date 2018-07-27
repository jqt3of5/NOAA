package com.example.jqt3of5.noaa.Weather

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.WeatherServiceZone
import com.example.jqt3of5.noaa.R
import com.example.jqt3of5.noaa.Repository.AlertsRepository
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

class WeatherAlertsFragment : Fragment()
{
    lateinit var weatherView : DailyWeatherView
    lateinit var alertList : RecyclerView
    lateinit var adapter : WeatherAlertsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.weather_fragment, container, true)
        weatherView = view.findViewById(R.id.daily_weather_view)
        alertList = view.findViewById(R.id.weather_alerts_recycler_view)

        alertList.layoutManager = LinearLayoutManager(this.context)
        adapter = WeatherAlertsAdapter()
        alertList.adapter = adapter

        val zoneCode = arguments!!["zone_code"] as String
        updateZoneCode(zoneCode)

        return view
    }

    fun updateZoneCode(zoneCode : String)
    {
        AlertsRepository().startGetForAlerts(zoneCode).observe(this, Observer {
            it?.let {
                adapter.updateWeatherAlerts(it)
            }
        })
    }
}