package com.example.jqt3of5.noaa

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.jqt3of5.noaa.Api.WeatherApi
import com.example.jqt3of5.noaa.Api.DataObjects.AlertCountsByLocation
import com.example.jqt3of5.noaa.Api.DataObjects.AreaAlert
import com.example.jqt3of5.noaa.RegionSelect.CountyFipsData
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader
import com.example.jqt3of5.noaa.RegionSelect.SpinnderDialogFragment
import com.example.jqt3of5.noaa.RegionSelect.SpinnerDialogSelectedItemListener
//import com.google.android.material.chip.Chip
//import com.google.android.material.chip.ChipGroup

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SpinnerDialogSelectedItemListener<CountyFipsData?> {

    var mAdapter = MainAdapter()
    lateinit var mRecyclerView : RecyclerView
    var mZoneCounts : Map<String, Int>? = null
    var mAvailableZones : Map<String, List<CountyFipsData>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        /*val chipGroup = findViewById(R.id.main_chip_group) as ChipGroup
        val chip = Chip(this.applicationContext)
        chip.text = "A Chip"
        chipGroup?.addView(chip, 0)*/

        val context = this.applicationContext
        mAvailableZones = FipsDataLoader().loadFipsData(context)


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.weather.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherApi::class.java)
        service.getAlertCounts().enqueue(object:Callback<AlertCountsByLocation>{
            override fun onResponse(call: Call<AlertCountsByLocation>?, response: Response<AlertCountsByLocation>?) {
                mZoneCounts = response?.body()?.zones
                showRegionSelectDialog()
            }

            override fun onFailure(call: Call<AlertCountsByLocation>?, t: Throwable?) {
                showRegionSelectDialog()
            }
        })

        val layout =  LinearLayoutManager(this)
        mRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
            adapter = mAdapter
            layoutManager =  layout
        }
    }

    fun showRegionSelectDialog()
    {
        val dialog = SpinnderDialogFragment()

        dialog.mCountyMap = mAvailableZones!!
        dialog.mZoneCountMap = mZoneCounts
        dialog.mStates = mAvailableZones!!.keys.toList().sorted()
        dialog.mTitle = "Select Zone"
        dialog.mListener = this
        dialog.show(supportFragmentManager, "state_select_fragment")
    }
    override fun ItemSelected(selection: CountyFipsData?) {
        selection?.let{
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.weather.gov")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(WeatherApi::class.java)

            service.getAlertByZone(it.getZoneCode()).enqueue(object:Callback<AreaAlert> {
                override fun onFailure(call: Call<AreaAlert>?, t: Throwable?) {

                }
                override fun onResponse(call: Call<AreaAlert>?, response: Response<AreaAlert>?) {
                    mAdapter?.areaData = response?.body()
                    mAdapter?.notifyDataSetChanged()
                }
            })
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            R.id.action_select_region -> {
                showRegionSelectDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
