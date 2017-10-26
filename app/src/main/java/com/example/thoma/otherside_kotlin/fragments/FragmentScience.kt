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

class FragmentScience : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_science, container, false)
    }

}