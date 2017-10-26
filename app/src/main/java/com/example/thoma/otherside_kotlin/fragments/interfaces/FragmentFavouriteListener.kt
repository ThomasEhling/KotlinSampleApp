package com.example.thoma.otherside_kotlin.fragments.interfaces

import com.example.thoma.otherside_kotlin.model.Tunnel

interface FragmentFavouriteListener {
    fun getFavoutiteTunnelsList(): ArrayList<Tunnel>
    fun setFavoutiteTunnelsList(tunnels: ArrayList<Tunnel>)
    fun addFavouriteTunnel(tunnel: Tunnel)
    fun removeFavouriteTunnel(tunnel: Tunnel)
    fun clearFavoutiteTunnelsList()
}