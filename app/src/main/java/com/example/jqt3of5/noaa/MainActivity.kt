package com.example.jqt3of5.noaa

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.jqt3of5.noaa.Repository.Api.DataObjects.AlertFeature
import com.example.jqt3of5.noaa.Repository.Data.MainDatabase
import com.example.jqt3of5.noaa.Preferences.NotificationPreferencesActivity
import com.example.jqt3of5.noaa.RegionSelect.FipsDataLoader
import com.example.jqt3of5.noaa.Repository.AlertsRepository
import com.example.jqt3of5.noaa.Repository.Data.Entities.WeatherAlert
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var mAdapter = MainAdapter()
    lateinit var mRecyclerView : RecyclerView

    var mData : MutableList<AlertFeature> = mutableListOf()

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

        MainDatabase.createInstance(this)
        FipsDataLoader.loadFipsData(this)

        val layout =  LinearLayoutManager(this)
        mRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
            adapter = mAdapter
            layoutManager =  layout
        }

        MainDatabase.getInstance().notifications().getAllNotifications().observe(this, Observer {
            //TODO: incrementally update the list, not all at once
            mAdapter.clear()

            it?.forEach {

                if (it.table == WeatherAlert.TABLE_NAME)
                {
                    MainDatabase.getInstance().weatherAlerts()
                            .selectById(it.foreign_key)
                            .subscribeOn(Schedulers.io())
                            .subscribe { it ->
                                it?.let{
                                    mAdapter.addAlert(it)
                                }

                            }
                }
            }
            mAdapter.notifyDataSetChanged()
        } )
    }

    override fun onResume() {
        super.onResume()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val zones = preferences.getStringSet("ews_zones", emptySet())
        zones.forEach {
            AlertsRepository().getAlertForZone(it)
        }
    }

    fun showPreferences()
    {
        val intent = Intent(this, NotificationPreferencesActivity::class.java)
        startActivity(intent)
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
            R.id.action_settings -> {
                showPreferences()
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
