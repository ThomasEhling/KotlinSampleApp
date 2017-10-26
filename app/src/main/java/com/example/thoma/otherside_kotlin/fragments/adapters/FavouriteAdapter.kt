package com.example.thoma.otherside_kotlin.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.model.Tunnel
import kotlinx.android.synthetic.main.fragment_favourite_viewholder.view.*
import java.util.*

/**
 * Created by thoma on 8/22/2017.
 */

class FavouriteAdapter(val onClickListener: FavouriteListener, var tunnelsList: ArrayList<Tunnel>) : RecyclerView.Adapter<FavouriteAdapter.TunnelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TunnelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.fragment_favourite_viewholder, parent, false)
        return TunnelViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: TunnelViewHolder, index: Int) {
        holder.bind(tunnelsList[index], onClickListener)
    }

    override fun getItemCount(): Int {
        if (tunnelsList.isEmpty()) {
            return 0
        }
        return tunnelsList.size
    }

    class TunnelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tunnel: Tunnel, onClickListener: FavouriteListener) = with(itemView) {

            favourite_tunnel_title.text = tunnel.title

            if (tunnel.addressOrigin == null) {
                favourite_tunnel_address.text = "35 street unknown"
            } else {
                favourite_tunnel_address.text = tunnel.addressOrigin
            }

            button_delete_favourite.setOnClickListener {
                tunnel.isFavourite = !tunnel.isFavourite
                onClickListener.deleteFavouriteButtonOnClick(tunnel)
            }

            this.setOnClickListener(View.OnClickListener {
                onClickListener.favouriteTunnelOnClick(tunnel)
            })
        }
    }

    interface FavouriteListener {
        fun deleteFavouriteButtonOnClick(tunnel: Tunnel)
        fun favouriteTunnelOnClick(tunnel: Tunnel)
    }

    fun setData(list: ArrayList<Tunnel>) {
        tunnelsList = list
        notifyDataSetChanged()
    }
}