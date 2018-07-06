package com.example.jqt3of5.noaa.Preferences

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jqt3of5.noaa.R

class NationalWeatherServiceLocationsFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.national_weather_service_locations_fragment, container, false)
        val recyclerView = view.findViewById(R.id.nws_locations_recycler_view) as RecyclerView
        recyclerView.adapter = LocationsAdapter(listOf("Seattle, Washington"))
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        return view;
    }
}