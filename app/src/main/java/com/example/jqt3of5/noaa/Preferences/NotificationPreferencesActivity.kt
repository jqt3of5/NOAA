package com.example.jqt3of5.noaa.Preferences

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.jqt3of5.noaa.R

class NotificationPreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_preferences_activity)

        val moreSettingsButton = findViewById(R.id.nws_more_settings_button) as Button
        moreSettingsButton.setOnClickListener {
            val intent = Intent(this,NationalWeatherServicePrefsActivity::class.java)
            startActivity(intent)
        }
    }
}