package com.example.jqt3of5.noaa

import android.content.Context
import android.database.DataSetObserver
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =  inflater.inflate(R.layout.notification_view, parent, false) as NotificationView
        //NotificationView(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notificationView.dateTextView.text = "1/2/3"
        holder.notificationView.sourceTextView.text = "NOAA"
        holder.notificationView.subtitleTextView.text = "Severe weather alert"
        holder.notificationView.titleTextView.text = "weather"
    }

    class ViewHolder(val notificationView : NotificationView) : RecyclerView.ViewHolder(notificationView)

}