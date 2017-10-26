package com.example.thoma.otherside_kotlin.fragments.interfaces

import android.app.Fragment

public interface FragmentChangeListener{
    public fun replaceFragment(fragment: Fragment): Unit
}