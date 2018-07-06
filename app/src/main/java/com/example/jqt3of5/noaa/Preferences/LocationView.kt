package com.example.jqt3of5.noaa.Preferences

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jqt3of5.noaa.R

class LocationView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    lateinit var locationName : TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        locationName = findViewById(R.id.location_text_view)
    }
}