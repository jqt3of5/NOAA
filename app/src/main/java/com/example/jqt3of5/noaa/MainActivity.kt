package com.example.jqt3of5.noaa

import android.app.ActionBar
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialog
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SpinnerDialogSelectedItemListener<String> {

    var mAdapter = MainAdapter()
    lateinit var mRecyclerView : RecyclerView
    var mRegions : List<String>? = null

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

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.weather.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherApi::class.java)
        service.getLocationCodes().enqueue(object:Callback<AlertCountsByLocation>{
            override fun onResponse(call: Call<AlertCountsByLocation>?, response: Response<AlertCountsByLocation>?) {
                mRegions = response?.body()?.areas?.keys?.distinct()
                showRegionSelectDialog()
            }

            override fun onFailure(call: Call<AlertCountsByLocation>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG)
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
        val context = this.applicationContext
        val dialog = SpinnderDialogFragment()

        dialog.mItems = mRegions
        dialog.mTitle = "Select Region"
        dialog.mListener = this
        dialog.show(supportFragmentManager, "state_select_fragment")
    }
    override fun ItemSelected(selection: String) {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.weather.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherApi::class.java)
        service.getAlertByArea(selection).enqueue(object:Callback<AreaAlert> {
            override fun onFailure(call: Call<AreaAlert>?, t: Throwable?) {

            }
            override fun onResponse(call: Call<AreaAlert>?, response: Response<AreaAlert>?) {
                mAdapter?.areaData = response?.body()
                mAdapter?.notifyDataSetChanged()
            }
        })
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
