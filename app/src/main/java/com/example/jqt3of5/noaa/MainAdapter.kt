package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Repository.Data.Entities.BlogPost
import com.example.jqt3of5.noaa.Repository.Data.Entities.INotifiableEntity
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    enum class NotificationViewTypes
    {
        NationalWeatherServiceAlerts, EmergencyZoneNotification, BlogPost
    }

    var showAllAlertsForZone : ((String) -> Unit)? = null
    private var mAlerts : MutableList<INotifiableEntity> = mutableListOf()

    fun setAlerts(alerts : List<INotifiableEntity>)
    {
        mAlerts.clear()
        mAlerts.addAll(alerts)
    }

    fun insertAlert(alert : INotifiableEntity)
    {
        if (!mAlerts.contains(alert))
        {
            mAlerts.add(0, alert)
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (mAlerts[position])
        {
            is WeatherAlert -> return 0
            is BlogPost -> return 1
        }

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
            NotificationViewTypes.EmergencyZoneNotification -> {
                val view =  inflater.inflate(R.layout.ez_notification_view, parent, false) as EmergencyZoneNotificationView
                return NotificationViewHolder(view)
            }
            NotificationViewTypes.BlogPost -> {
                val view =  inflater.inflate(R.layout.ez_notification_view, parent, false) as EmergencyZoneNotificationView
                return NotificationViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
       return mAlerts.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder.itemView
        when (view)
        {
            is NationalWeatherServiceNotificationView -> {
                val weatherAlert = mAlerts[position] as WeatherAlert
                view.weatherAlertView.bind(weatherAlert)
                view.showAllAlertsButton.setOnClickListener {
                    showAllAlertsForZone?.invoke(weatherAlert.zoneCode)
                }
            }

            is EmergencyZoneNotificationView -> {

            }
        }
    }

    class NotificationViewHolder(val notificationView : CardView) : RecyclerView.ViewHolder(notificationView)
}