package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.AlertFeature
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    enum class NotificationViewTypes
    {
        NationalWeatherServiceAlerts, EmergencyZone,
    }

    var showAllAlertsForZone : ((String) -> Unit)? = null
    private var alerts : MutableList<WeatherAlert> = mutableListOf()

    fun clear()
    {
        alerts.clear()
    }
    fun addAlert(alert : WeatherAlert)
    {
        if (!alerts.contains(alert))
        {
            alerts.add(0, alert)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        when (NotificationViewTypes.values()[viewType])
        {
            NotificationViewTypes.NationalWeatherServiceAlerts -> {
                val view =  inflater.inflate(R.layout.nws_notification_view, parent, false) as NationalWeatherServiceNotificationView
                return NotificationViewHolder(view)
            }
            NotificationViewTypes.EmergencyZone -> {
                val view =  inflater.inflate(R.layout.ez_notification_view, parent, false) as EmergencyZoneNotificationView
                return NotificationViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
       return alerts.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder.itemView
        when (view)
        {
            is NationalWeatherServiceNotificationView -> {
                view.weatherAlertView.bind(alerts[position])
                view.showAllAlertsButton.setOnClickListener {
                    showAllAlertsForZone?.invoke(alerts[position].zoneCode)
                }
            }
            is EmergencyZoneNotificationView -> {

            }
        }
    }

    class NotificationViewHolder(val notificationView : CardView) : RecyclerView.ViewHolder(notificationView)
}