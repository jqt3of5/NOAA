package com.example.jqt3of5.noaa.Preferences

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jqt3of5.noaa.R

class LocationsAdapter(val locations : List<String>) : RecyclerView.Adapter<LocationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.location_view, parent, false) as LocationView
        return LocationsViewHolder(view)
    }

    override fun getItemCount(): Int {
       return locations.count()
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = locations[position]
        holder.view.locationName.text = location
    }
}

class LocationsViewHolder(val view : LocationView) : RecyclerView.ViewHolder(view){}