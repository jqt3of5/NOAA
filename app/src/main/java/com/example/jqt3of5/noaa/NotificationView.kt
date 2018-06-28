package com.example.jqt3of5.noaa

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.widget.TextView

class NotificationView :ConstraintLayout {

    lateinit var titleTextView : TextView
    lateinit var subtitleTextView : TextView
    lateinit var sourceTextView : TextView
    lateinit var dateTextView : TextView

   constructor(context:Context) : super(context)
   {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.notification_view, this, true)
   }

    override fun onFinishInflate() {
        super.onFinishInflate()

        titleTextView = findViewById(R.id.notification_title)
        subtitleTextView = findViewById(R.id.notification_subtitle)
        sourceTextView = findViewById(R.id.notification_source)
        dateTextView = findViewById(R.id.notification_date)
    }

}