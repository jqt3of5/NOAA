package com.example.jqt3of5.noaa

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.AppCompatSpinner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*

class SpinnderDialogFragment : DialogFragment() {

    lateinit var mStateSpinner : AppCompatSpinner
    lateinit var mCountySpinner : AppCompatSpinner

    var mCountyMap : Map<String, List<CountyFipsData>>? = null

    var mStatesAdapter : ArrayAdapter<String>? = null
    var mCountyAdapter : ArrayAdapter<String>? = null

    var mTitle : String? = null
    var mListener : SpinnerDialogSelectedItemListener<CountyFipsData?>? = null

    var selectedState : String? = null
    var selectedCounty : CountyFipsData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.spinner_dialog_fragment, container)

        mStateSpinner = view.findViewById(R.id.dialog_state_spinner) as AppCompatSpinner
        mStatesAdapter = ArrayAdapter<String>(context, R.layout.simple_spinner_layout)
        mStateSpinner.adapter = mStatesAdapter
        mStateSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0)
                {
                    selectedState = mCountyMap!!.keys.toList().get(position-1)
                    mCountyAdapter!!.clear()
                    val counties = mCountyMap!!.get(selectedState!!)?.map {it.countyName}
                    mCountyAdapter!!.addAll(counties)
                    mCountyAdapter!!.notifyDataSetChanged()
                    mCountySpinner.visibility = View.VISIBLE
                }
                else
                {
                    selectedState = null
                    mCountySpinner.visibility = View.GONE
                }
            }
        }

        mCountySpinner = view.findViewById(R.id.dialog_county_spinner) as AppCompatSpinner
        mCountyAdapter = ArrayAdapter<String>(context, R.layout.simple_spinner_layout)
        mCountySpinner.adapter = mCountyAdapter
        mCountySpinner.visibility = View.GONE
        mCountySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCounty = mCountyMap!!.get(selectedState!!)?.get(position)
            }
        }

        val title = view.findViewById(R.id.spinner_dialog_title) as TextView
        title.text = mTitle

        val button = view.findViewById(R.id.spinner_dialog_confirm) as Button
        button.setOnClickListener{
            mListener?.ItemSelected(selectedCounty)
            dialog.dismiss()
        }

        val states = mCountyMap?.keys?.toList()?.toMutableList() ?: mutableListOf()
        states?.add(0, "Please Select a State...")
        mStatesAdapter!!.addAll(states)
        mStatesAdapter!!.notifyDataSetChanged()

        return view
    }


}