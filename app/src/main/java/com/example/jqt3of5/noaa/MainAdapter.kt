package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Api.DataObjects.AlertFeature
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader

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
                val view =  inflater.inflate(R.layout.nws_notification_view, parent, false) as NationalWeatherServiceNotificationView
                return NWSViewHolder(view)
            }
            NotificationViewTypes.EmergencyZone -> {
                val view =  inflater.inflate(R.layout.ez_notification_view, parent, false) as EmergencyZoneNotificationView
                return EZViewHolder(view)
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

        when (holder)
        {
            is EZViewHolder -> {

            }
            is NWSViewHolder -> {
                val properties = features?.get(position)?.properties
                holder.notificationView.dateTextView.text = properties?.sent?.toString()
                holder.notificationView.eventTextView.text = properties?.event
                holder.notificationView.areaDescriptionTextView.text = properties?.description?.replace("\n", " ")
                holder.notificationView.zoneTextView.text = FipsDataLoader.zoneToCountyMap?.get(zoneCodes[position])

                when(properties?.severity)
                {
                    "Severe" -> holder.notificationView.setSeverity(Severity.Severe)
                    "Moderate" -> holder.notificationView.setSeverity(Severity.Moderate)
                    else -> holder.notificationView.setSeverity(Severity.Unknown)
                }
            }
        }
    }

    class EZViewHolder(val notificationView : EmergencyZoneNotificationView) : RecyclerView.ViewHolder(notificationView)
    class NWSViewHolder(val notificationView : NationalWeatherServiceNotificationView) : RecyclerView.ViewHolder(notificationView)

}