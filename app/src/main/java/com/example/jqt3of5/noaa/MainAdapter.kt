package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Api.DataObjects.AlertFeature
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>()
{
    private var features : MutableList<AlertFeature> = mutableListOf()
    private var zoneCodes : MutableList<String> = mutableListOf()

    fun clearAlerts()
    {
        features.clear()
        zoneCodes.clear()
    }
    fun addAlert(code :String, feature : AlertFeature)
    {
        features.add(0, feature)
        zoneCodes.add(0, code)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =  inflater.inflate(R.layout.notification_view, parent, false) as NotificationView

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       features?.let {
            return it.count()
        }
       return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

        /*when(properties?.event)
        {
            "Excessive Heat Warning" -> holder.notificationView.setEventType(NotificationEvent.Heat)
            "Heat Advisory" -> holder.notificationView.setEventType(NotificationEvent.Heat)

            "Beach Hazards Statement" -> holder.notificationView.setEventType(NotificationEvent.Beach)

            "Severe Thunderstorm Warning" -> holder.notificationView.setEventType(NotificationEvent.Lightning)

            "Red Flag Warning" -> holder.notificationView.setEventType(NotificationEvent.Fire)
            "Fire Weather Watch" -> holder.notificationView.setEventType(NotificationEvent.Fire)

            "Flood Advisory" -> holder.notificationView.setEventType(NotificationEvent.Flood)

            else -> holder.notificationView.setEventType(NotificationEvent.Unknown)
        }*/
    }

    class ViewHolder(val notificationView : NotificationView) : RecyclerView.ViewHolder(notificationView)

}