package com.example.thoma.ontheotherside_kotlin.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thoma.otherside_kotlin.R
import kotlinx.android.synthetic.main.fragment_about.*
import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.support.design.widget.Snackbar


class FragmentAbout : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        about_app_mail_button.setOnClickListener(View.OnClickListener {
            copyToClipboard(getString(R.string.app_email))
            Snackbar.make(about_main_layout, "Application email copied successfully", Snackbar.LENGTH_LONG).show()
        })
        about_dev_mail_button.setOnClickListener(View.OnClickListener {
            copyToClipboard(getString(R.string.dev_email))
            Snackbar.make(about_main_layout, "Developper email copied successfully", Snackbar.LENGTH_LONG).show()
        })
        about_github_button.setOnClickListener(View.OnClickListener {
            copyToClipboard(getString(R.string.github_link))
            Snackbar.make(about_main_layout, "Github link copied successfully", Snackbar.LENGTH_LONG).show()
        })

    }

    private fun copyToClipboard(info: String) {
        val clipboard = activity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("OtherSideInfo", info)
        clipboard.setPrimaryClip(clip)
    }

}