package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.jqt3of5.noaa.Api.DataObjects.AlertFeature
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader
import com.example.jqt3of5.noaa.Weather.WeatherAlertView

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    enum class NotificationViewTypes
    {
        NationalWeatherServiceAlerts, EmergencyZone,
    }
    private var features : MutableList<AlertFeature> = mutableListOf()
    private var zoneCodes : MutableList<String> = mutableListOf()

    fun addAlert(code :String, feature : AlertFeature)
    {
        features.add(0, feature)
        zoneCodes.add(0, code)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        when (NotificationViewTypes.values()[viewType])
        {
            NotificationViewTypes.NationalWeatherServiceAlerts -> {
                val view =  inflater.inflate(R.layout.weather_alert_view, parent, false) as NationalWeatherServiceNotificationView
                return NotificationViewHolder(view)
            }
            NotificationViewTypes.EmergencyZone -> {
                val view =  inflater.inflate(R.layout.ez_notification_view, parent, false) as EmergencyZoneNotificationView
                return NotificationViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
       features?.let {
            return it.count()
        }
       return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder.itemView
        when (view)
        {
            is NationalWeatherServiceNotificationView -> {
                //val properties = features
                //view.weatherAlertView.bind(properties)
            }
            is EmergencyZoneNotificationView -> {

            }
        }
    }

    class NotificationViewHolder(val notificationView : CardView) : RecyclerView.ViewHolder(notificationView)
}