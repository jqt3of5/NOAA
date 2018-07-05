package com.example.jqt3of5.noaa

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

class NotificationsPreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
       setPreferencesFromResource(R.xml.notification_preferences,rootKey)
    }

}