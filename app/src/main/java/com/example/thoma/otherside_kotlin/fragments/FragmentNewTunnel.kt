package com.example.thoma.ontheotherside_kotlin.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import kotlinx.android.synthetic.main.fragment_new_tunnel.*

class FragmentNewTunnel : Fragment() {

    public final val NULL_LONGITUDE = 200
    public final val NULL_LATTITUDE = 200

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_new_tunnel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        dig_button.setOnClickListener(View.OnClickListener {
            val titleTunnel: String? = title_tunnel.text.toString();
            val addressTunnel: String? = address_tunnel.text.toString();
            val latTunnel: String? = latitude_tunnel.text.toString();
            val longTunnel: String? = longitude_tunnel.text.toString();

            var latTunnelInt: Int = NULL_LATTITUDE
            var longTunnelInt: Int = NULL_LONGITUDE

            if (!latTunnel.isNullOrEmpty() && !longTunnel.isNullOrEmpty()) {
                latTunnelInt = try {
                    latTunnel!!.toInt()
                } catch (e: NumberFormatException) {
                    NULL_LATTITUDE
                }

                longTunnelInt = try {
                    longTunnel!!.toInt()
                } catch (e: NumberFormatException) {
                    NULL_LONGITUDE
                }
            }

            val correctlyFilled = (!titleTunnel.isNullOrEmpty() && (!addressTunnel.isNullOrEmpty() || (latTunnelInt != NULL_LATTITUDE && longTunnelInt != NULL_LONGITUDE)))

            if (!correctlyFilled) {
                Snackbar.make(layout_entrance, "Please fill All require place", Snackbar.LENGTH_LONG).show()
            } else {
                val frag = FragmentExitTunnel()
                val args = Bundle()

                args.putInt("latitude", latTunnelInt)
                args.putInt("longitude", longTunnelInt)
                args.putString("title", titleTunnel)
                args.putString("address", addressTunnel)

                frag.setArguments(args)

                val fragmentChanger: FragmentChangeListener = activity as FragmentChangeListener;
                fragmentChanger.replaceFragment(frag)

            }
        })
    }


}