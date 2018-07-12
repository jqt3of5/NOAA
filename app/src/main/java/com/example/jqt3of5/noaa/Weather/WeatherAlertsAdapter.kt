package com.example.jqt3of5.noaa.Weather

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert
import com.example.jqt3of5.noaa.Preferences.LocationView
import com.example.jqt3of5.noaa.R
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader

class WeatherAlertsAdapter : RecyclerView.Adapter<WeatherAlertsAdapter.WeatherAlertViewHolder>()
{
    private var weatherAlert : AreaAlert? = null

    fun updateWeatherAlerts(alert : AreaAlert)
    {
        weatherAlert = alert
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAlertViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.weather_alert_view, parent, false) as WeatherAlertView
        return WeatherAlertViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherAlert?.features?.size ?: 0
    }

    override fun onBindViewHolder(holder: WeatherAlertViewHolder, position: Int) {
        val view = holder.itemView as WeatherAlertView

        weatherAlert?.features?.get(position)?.properties?.let {
            view.bind(it)
        }
    }

    class WeatherAlertViewHolder(view : WeatherAlertView) : RecyclerView.ViewHolder(view)
}