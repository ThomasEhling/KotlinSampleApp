package com.example.thoma.otherside_kotlin.fragments

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.example.thoma.ontheotherside_kotlin.fragments.FragmentAbout
import com.example.thoma.ontheotherside_kotlin.fragments.FragmentNewTunnel
import com.example.thoma.otherside_kotlin.R
import com.example.thoma.otherside_kotlin.fragments.interfaces.FragmentChangeListener
import kotlinx.android.synthetic.main.fragment_settings.*

class FragmentSettings : Fragment(), CompoundButton.OnCheckedChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        settings_color_switch.setOnCheckedChangeListener(this)

        settings_about_button.setOnClickListener({
            val fragmentChanger: FragmentChangeListener = activity as FragmentChangeListener;
            fragmentChanger.replaceFragment(FragmentAbout())
        })
    }

    override fun onCheckedChanged(buttonView:  CompoundButton?, isChecked : Boolean) {
       if(isChecked){
           Snackbar.make(view, "NIGHT COLOR MODE ON", Snackbar.LENGTH_LONG).show()
       }else{
           Snackbar.make(view, "NIGHT COLOR MODE OFF", Snackbar.LENGTH_LONG).show()
       }
    }

}

