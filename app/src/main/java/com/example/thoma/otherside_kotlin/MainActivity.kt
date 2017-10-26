package com.example.thoma.otherside_kotlin

import android.app.AlertDialog
import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.thoma.ontheotherside_kotlin.fragments.*
import com.example.thoma.otherside_kotlin.fragments.FragmentSettings
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentFavouriteListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentHistoricListener
import com.example.thoma.otherside_kotlin.model.Tunnel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FragmentChangeListener, FragmentHistoricListener, FragmentFavouriteListener {

    private var listHistoricTunnels: ArrayList<Tunnel>
    private var listFavouriteTunnels: ArrayList<Tunnel>
    private var homeFragmentActuallyDisplayed: Boolean

    init {
        listHistoricTunnels = ArrayList<Tunnel>()
        listFavouriteTunnels = ArrayList<Tunnel>()
        homeFragmentActuallyDisplayed = true;
    }

    constructor(parcel: android.os.Parcel) : this() {
        homeFragmentActuallyDisplayed = parcel.readByte() != 0.toByte()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayView(R.id.nav_new_tunnel)

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            //We want to hide the keybord when the drawer is opened
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        if (!homeFragmentActuallyDisplayed) {
            displayView(R.id.nav_new_tunnel)
        } else {
            AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle("Quitting the App")
                    .setMessage("Are you sure you want to quit so soon?")
                    .setPositiveButton("ОК") { dialog, whichButton -> finish() }
                    .setNegativeButton("NO", null)
                    .show();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                displayView(R.id.nav_settings)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayView(item.getItemId());
        return true
    }

    fun displayView(viewId: Int) {

        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        homeFragmentActuallyDisplayed = false

        when (viewId) {
            R.id.nav_new_tunnel -> {
                fragment = FragmentNewTunnel()
                title = "New Tunnel"
                homeFragmentActuallyDisplayed = true
            }
            R.id.nav_list_tunnels -> {
                fragment = FragmentFavourite()
                title = "Favorites Tunnels"
            }
            R.id.nav_historic -> {
                fragment = FragmentHistoric()
                title = "Historic"
            }
            R.id.nav_popular -> {
                fragment = FragmentHistoric()
                title = "Parameters"
            }
            R.id.nav_settings -> {
                fragment = FragmentSettings()
                title = "Parameters"
            }
            R.id.nav_science -> {
                fragment = FragmentScience()
                title = "Science"
            }
            R.id.nav_about -> {
                fragment = FragmentAbout()
                title = getString(R.string.app_name)
            }
        }

        if (fragment != null) {
            val ft = getFragmentManager().beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.commit()
        }

        // set the toolbar title
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(title)
        }

        //because we change fragmentsa lot, we need to close the softkeyboard if it's open
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(currentFocus!=null){
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun getHistoricTunnelsList(): ArrayList<Tunnel> {
        return listHistoricTunnels
    }

    override fun setHistoricTunnelsList(tunnels: ArrayList<Tunnel>) {
        listHistoricTunnels = tunnels
    }

    override fun addHistoricTunnel(tunnel: Tunnel) {
        listHistoricTunnels.add(tunnel)
    }

    override fun clearHistoricTunnelsList() {
        listHistoricTunnels.clear()
    }

    override fun getFavoutiteTunnelsList(): ArrayList<Tunnel> {
        return listFavouriteTunnels
    }

    override fun setFavoutiteTunnelsList(tunnels: ArrayList<Tunnel>) {
        listFavouriteTunnels = tunnels
    }

    override fun addFavouriteTunnel(tunnel: Tunnel) {
        listFavouriteTunnels.add(tunnel)
    }

    override fun removeFavouriteTunnel(tunnel: Tunnel) {
        listFavouriteTunnels.remove(tunnel)
    }

    override fun clearFavoutiteTunnelsList() {
        listFavouriteTunnels.clear()
    }


    override fun replaceFragment(fragment: Fragment) {

        //because we change fragmentsa lot, we need to close the softkeyboard if it's open
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(currentFocus!=null){
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        val ft = getFragmentManager().beginTransaction()
        ft.replace(R.id.content_frame, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

}
