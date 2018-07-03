package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>()
{
    var areaData : AreaAlert? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =  inflater.inflate(R.layout.notification_view, parent, false) as NotificationView

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        areaData?.features?.let {
            return it.count()
        }
       return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val properties = areaData?.features?.get(position)?.properties
        holder.notificationView.dateTextView.text = properties?.sent?.toString()
        holder.notificationView.eventTextView.text = properties?.event
        holder.notificationView.areaDescriptionTextView.text = properties?.areaDesc
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