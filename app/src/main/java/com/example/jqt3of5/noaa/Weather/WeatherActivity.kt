package com.example.jqt3of5.noaa.Weather

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.jqt3of5.noaa.R

//Presented when a weather alert notification is clicked for "Show more information". Mainly just houses the fragment
class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = FrameLayout(this)
        layout.id = View.generateViewId()
        setContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))

        if (savedInstanceState == null)
        {
            val fragment = WeatherAlertsFragment()
            fragment.arguments = intent.extras
            supportFragmentManager.beginTransaction().add(layout.id, fragment).commit()
        }
    }
}