package com.example.jqt3of5.noaa.Weather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert
import com.example.jqt3of5.noaa.R

class WeatherAlertsFragment : Fragment()
{
    lateinit var weatherView : DailyWeatherView
    lateinit var alertList : RecyclerView
    lateinit var adapter : WeatherAlertsAdapter

    private var mAlert : AreaAlert? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.weather_fragment, container, true)
        weatherView = view.findViewById(R.id.daily_weather_view)
        alertList = view.findViewById(R.id.weather_alerts_recycler_view)

        alertList.layoutManager = LinearLayoutManager(this.context)
        adapter = WeatherAlertsAdapter()
        alertList.adapter = adapter

        mAlert?.let {
            adapter.updateWeatherAlerts(it)
        }

        return view
    }

    fun updateAlert(alert : AreaAlert)
    {
        mAlert = alert
        adapter?.updateWeatherAlerts(alert)
    }
}