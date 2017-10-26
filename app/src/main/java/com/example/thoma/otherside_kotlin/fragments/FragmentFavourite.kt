package com.example.thoma.ontheotherside_kotlin.fragments

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.fragments.adapters.FavouriteAdapter
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentFavouriteListener
import com.example.thoma.otherside_kotlin.model.Tunnel
import kotlinx.android.synthetic.main.fragment_favourite.*

class FragmentFavourite : Fragment(), FavouriteAdapter.FavouriteListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        val favouriteInterface = activity as FragmentFavouriteListener;
        val tunnelsList = favouriteInterface.getFavoutiteTunnelsList()
        val favouriteAdapter = FavouriteAdapter(this, tunnelsList)

        if (tunnelsList.isEmpty()) {
            empty_favourite_text_view.visibility = View.VISIBLE
            recycler_view_favourite.visibility = View.GONE
        } else {
            empty_favourite_text_view.visibility = View.GONE
            recycler_view_favourite.visibility = View.VISIBLE
            val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val mDividerItemDecoration = DividerItemDecoration(activity, layoutManager.getOrientation())
            recycler_view_favourite.setLayoutManager(layoutManager)
            recycler_view_favourite.setHasFixedSize(true)
            recycler_view_favourite.addItemDecoration(mDividerItemDecoration)
            recycler_view_favourite.setAdapter(favouriteAdapter)
        }

    }

    override fun deleteFavouriteButtonOnClick(tunnel: Tunnel) {
        AlertDialog.Builder(activity)
                .setIcon(R.drawable.picture_favourite_tunnel)
                .setTitle("Delete the Tunnel from Favourite")
                .setMessage("Are you sure you want to delete this Tunnel from favourite?")
                .setPositiveButton("ОК") { dialog, whichButton -> unFavourite(tunnel) }
                .setNegativeButton("NO", null)
                .show()
    }

    fun unFavourite(tunnel: Tunnel) {
        val favouriteListener = activity as FragmentFavouriteListener
        favouriteListener.removeFavouriteTunnel(tunnel)
        val adapter: FavouriteAdapter = recycler_view_favourite.adapter as FavouriteAdapter
        adapter.setData(favouriteListener.getFavoutiteTunnelsList())
    }


    override fun favouriteTunnelOnClick(tunnel: Tunnel) {
        AlertDialog.Builder(activity)
                .setIcon(R.drawable.ic_picture_tunnel)
                .setTitle("Entering the Tunnel")
                .setMessage("Are you sure you want to jump into this Tunnel?")
                .setPositiveButton("ОК") { dialog, whichButton -> goToExitTunnel(tunnel) }
                .setNegativeButton("NO", null)
                .show()
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
