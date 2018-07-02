package com.example.jqt3of5.noaa

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.AppCompatSpinner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.TextView

class SpinnderDialogFragment : DialogFragment() {

    lateinit var mSpinner : AppCompatSpinner
    var mItems : List<String>? = null
    var mTitle : String? = null
    var mListener : SpinnerDialogSelectedItemListener<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.spinner_dialog_fragment, container)
        mSpinner = view.findViewById(R.id.dialog_spinner) as AppCompatSpinner

        val items = mItems?.toMutableList() ?: mutableListOf()
        items?.add(0, "Please Select a Region...")
        mSpinner.adapter = ArrayAdapter<String>(context, R.layout.simple_spinner_layout, items)

        val title = view.findViewById(R.id.spinner_dialog_title) as TextView
        title.text = mTitle

        val button = view.findViewById(R.id.spinner_dialog_confirm) as Button
        button.setOnClickListener{
            mListener?.ItemSelected(mSpinner.selectedItem as String)
            dialog.dismiss()
        }
        return view
    }


}