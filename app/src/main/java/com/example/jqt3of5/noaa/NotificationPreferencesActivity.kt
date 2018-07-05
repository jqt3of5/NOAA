package com.example.jqt3of5.noaa

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class NotificationPreferencesActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_preferences_activity)
      //  supportFragmentManager.beginTransaction().replace(R.id.content, NotificationsPreferenceFragment()).commit()
    }
}