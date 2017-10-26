package com.example.thoma.ontheotherside_kotlin.fragments

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.fragments.adapters.HistoricAdapter
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentFavouriteListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentHistoricListener
import com.example.thoma.otherside_kotlin.model.Tunnel
import kotlinx.android.synthetic.main.fragment_historic.*
import kotlinx.android.synthetic.main.fragment_historic_viewholder.*

class FragmentHistoric : Fragment(), HistoricAdapter.HistoricListener {

    var tunnelsList: ArrayList<Tunnel> = ArrayList<Tunnel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val historicInterface: FragmentHistoricListener = activity as FragmentHistoricListener;
        tunnelsList = historicInterface.getHistoricTunnelsList()

        return inflater.inflate(R.layout.fragment_historic, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        if (tunnelsList.isEmpty()) {
            empty_historic_text_view.visibility = View.VISIBLE
            recycler_view_historic.visibility = View.GONE
        } else {
            empty_historic_text_view.visibility = View.GONE
            recycler_view_historic.visibility = View.VISIBLE

            val adapter = HistoricAdapter(this, tunnelsList)
            val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val mDividerItemDecoration = DividerItemDecoration(activity, layoutManager.getOrientation())
            recycler_view_historic.setLayoutManager(layoutManager)
            recycler_view_historic.setHasFixedSize(true)
            recycler_view_historic.addItemDecoration(mDividerItemDecoration)
            recycler_view_historic.setAdapter(adapter)
        }

    }

    override fun favouriteButtonOnClick(tunnel: Tunnel) {
        var message = ""
        val favouriteListener = activity as FragmentFavouriteListener
        if (tunnel.isFavourite) {
            favouriteListener.addFavouriteTunnel(tunnel)
            button_favourite.setImageResource(R.drawable.ic_star_black_24dp)
            message = "Successfully add to favourite"
        } else {
            favouriteListener.removeFavouriteTunnel(tunnel)
            button_favourite.setImageResource(R.drawable.ic_star_border_black_24dp)
            message = "Successfully remove from favourite"
        }
        Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show()
    }


    override fun historicTunnelOnClick(tunnel: Tunnel) {

        AlertDialog.Builder(activity)
                .setIcon(R.drawable.ic_picture_tunnel)
                .setTitle("Entering the Tunnel")
                .setMessage("Are you sure you want to jump into this Tunnel?")
                .setPositiveButton("ОК") { dialog, whichButton -> goToExitTunnel(tunnel) }
                .setNegativeButton("NO", null)
                .show();

    }

    fun goToExitTunnel(tunnel: Tunnel) {
        val frag = FragmentExitTunnel()
        val args = Bundle()

        args.putInt("latitude", tunnel.entrance_lat)
        args.putInt("longitude", tunnel.entrance_long)
        args.putString("title", tunnel.title)
        args.putString("address", tunnel.addressOrigin)

        frag.setArguments(args)

        val fragmentChanger: FragmentChangeListener = activity as FragmentChangeListener;
        fragmentChanger.replaceFragment(frag)
    }


}
