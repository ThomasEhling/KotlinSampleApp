package com.example.thoma.ontheotherside_kotlin.fragments

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentHistoricListener
import com.example.thoma.otherside_kotlin.model.Tunnel
import kotlinx.android.synthetic.main.fragment_exit_tunnel.*

class FragmentExitTunnel : Fragment() {

    public final val NULL_LONGITUDE = 200
    public final val NULL_LATTITUDE = 200
    var tunnel: Tunnel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val myTunnel = Tunnel(arguments.getString("address"))

        myTunnel.title = arguments.getString("title")
        myTunnel.entrance_lat = arguments.getInt("latitude")
        myTunnel.entrance_long = arguments.getInt("longitude")


        if(myTunnel.entrance_lat == NULL_LATTITUDE || myTunnel.entrance_long == NULL_LATTITUDE){
//            val coord:ArrayList<Int>? = CoordinateFromAddress(myTunnel.addressOrigin).execute()
//            if(coord!=null){
//                myTunnel.entrance_lat = coord[0]
//                myTunnel.entrance_long = coord[1]
//            }
        }

        tunnel = myTunnel

        return inflater.inflate(R.layout.fragment_exit_tunnel, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        val historicListener: FragmentHistoricListener = activity as FragmentHistoricListener
        if(!historicListener.getHistoricTunnelsList().contains(tunnel)){
            historicListener.addHistoricTunnel(tunnel!!)
        }

        exit_title.text = tunnel!!.title
        exit_long_lat.text = """Exit coordinates :
                                lat : ${tunnel!!.entrance_lat}
                                long : ${tunnel!!.entrance_long}"""

        button_dig_again.setOnClickListener(View.OnClickListener {
            val fragmentChanger: FragmentChangeListener = activity as FragmentChangeListener;
            fragmentChanger.replaceFragment(FragmentNewTunnel())
        })

    }


}