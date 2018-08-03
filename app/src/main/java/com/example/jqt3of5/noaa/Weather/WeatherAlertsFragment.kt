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
    lateinit var mAdapter : WeatherAlertsAdapter
    lateinit var rootView : View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherView = rootView.findViewById(R.id.daily_weather_view)
        weatherView.onItemSelected {
            updateZoneCode(it.getZoneCode())
        }
        val layout = LinearLayoutManager(this.activity)
        mAdapter = WeatherAlertsAdapter()

        alertList = rootView.findViewById<RecyclerView>(R.id.weather_alerts_recycler_view).apply {
            layoutManager =  layout
            adapter = mAdapter
        }

        val countyName = arguments!!["county"] as String
        weatherView.searchTextView.setText(countyName, true)

        val zoneCode = arguments!!["zone"] as String
        updateZoneCode(zoneCode)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.weather_fragment, container, false)

        return rootView
    }

    fun updateZoneCode(zoneCode : String)
    {
        AlertsRepository().startGetForAlerts(zoneCode).observe(this, Observer {
            it?.let {
                mAdapter.updateWeatherAlerts(it)
            }
        })
    }
}