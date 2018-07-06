package com.example.jqt3of5.noaa.Preferences

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.jqt3of5.noaa.R

class EmergencyZonePrefsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
       setPreferencesFromResource(R.xml.emergency_zone_preferences,rootKey)
    }
}