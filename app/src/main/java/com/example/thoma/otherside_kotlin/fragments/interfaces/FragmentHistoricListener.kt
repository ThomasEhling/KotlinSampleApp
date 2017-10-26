package com.example.thoma.otherside_kotlin.fragments.interfaces

import com.example.thoma.otherside_kotlin.model.Tunnel

interface FragmentHistoricListener {
    fun getHistoricTunnelsList(): ArrayList<Tunnel>
    fun setHistoricTunnelsList(tunnels: ArrayList<Tunnel>)
    fun addHistoricTunnel(tunnel: Tunnel)
    fun clearHistoricTunnelsList()
}