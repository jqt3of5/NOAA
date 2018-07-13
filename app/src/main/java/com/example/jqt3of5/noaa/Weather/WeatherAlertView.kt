package com.example.jqt3of5.noaa.Weather

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.AlertProperties
import com.example.jqt3of5.noaa.R
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader

/*enum class NotificationEvent(val redId : Int)
{
    Beach(R.drawable.ic_warning_black_24dp),
    Wind(R.drawable.ic_warning_black_24dp),
    Heat(R.drawable.ic_brightness_high_black_24dp),
    Fire(R.drawable.ic_fire_black_24dp),
    Flood(R.drawable.ic_warning_black_24dp),
    Tornado(R.drawable.ic_warning_black_24dp),
    Lightning(R.drawable.ic_lightning_black_24dp),
    Rain(R.drawable.ic_cloud_black_24dp),
    Smoke(R.drawable.ic_lightning_black_24dp),
    Unknown(R.drawable.ic_warning_black_24dp)
}*/

enum class Severity(val colorId : Int, var str : String)
{
    Severe(R.color.colorSevere, "Severe"),
    Moderate(R.color.colorModerate, "Moderate"),
    Unknown(R.color.colorPrimaryDark, "Unknown")
}
class WeatherAlertView(context:Context, attrSet:AttributeSet ) : ConstraintLayout(context, attrSet) {

    lateinit var eventTextView : TextView
    lateinit var severityTextView : TextView
    lateinit var dateTextView : TextView
    lateinit var eventTypeIcon : ImageView
    lateinit var areaDescriptionTextView : TextView
    lateinit var zoneTextView : TextView

    override fun onFinishInflate() {
        super.onFinishInflate()

        eventTextView = findViewById(R.id.notification_event)
        severityTextView = findViewById(R.id.notification_severity)
        dateTextView = findViewById(R.id.notification_date)
        eventTypeIcon = findViewById(R.id.event_icon) as ImageView
        eventTypeIcon.visibility = GONE
        areaDescriptionTextView = findViewById(R.id.notification_area_description)
        zoneTextView = findViewById(R.id.notification_zone)

    }

    fun bind(property : AlertProperties)
    {
        dateTextView.text = property?.sent?.toString()
        eventTextView.text = property?.event
        areaDescriptionTextView.text = property?.description?.replace("\n", " ")

        when(property?.severity)
        {
            "Severe" -> setSeverity(Severity.Severe)
            "Moderate" -> setSeverity(Severity.Moderate)
            else -> setSeverity(Severity.Unknown)
        }
    }

    fun bind(property : AlertProperties, zoneCode : String)
    {
        zoneTextView.text = FipsDataLoader.zoneToCountyMap?.get(zoneCode)
        bind(property)
    }
    fun setSeverity(severity : Severity)
    {
        severityTextView.text = severity.str
        when(severity)
        {
            Severity.Severe -> severityTextView.setTextColor(resources.getColor(R.color.colorSevere))
            Severity.Moderate -> severityTextView.setTextColor(resources.getColor(R.color.colorModerate))
            else -> severityTextView.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
    }

}