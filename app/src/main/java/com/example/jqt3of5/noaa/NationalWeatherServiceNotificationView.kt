package com.example.jqt3of5.noaa

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.jqt3of5.noaa.R
import com.example.jqt3of5.noaa.Weather.WeatherAlertView

class NationalWeatherServiceNotificationView(context:Context, attrSet:AttributeSet ) : CardView(context, attrSet) {

    lateinit var weatherAlertView : WeatherAlertView

    override fun onFinishInflate() {
        super.onFinishInflate()
        weatherAlertView = findViewById(R.id.weather_alert_view)

        val button = findViewById(R.id.see_more_alerts_button) as Button
        button.setOnClickListener {

        }
    }
}