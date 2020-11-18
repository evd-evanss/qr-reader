package com.sugarspoon.qrreader.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sugarspoon.qrreader.extensions.setVisible
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

open class BaseActivity : AppCompatActivity() {

    var view: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        view = (window.decorView.rootView as? ViewGroup)
    }

    fun setToolbar(title: String, displayHomeAsUpEnabled: Boolean = false) = view?.run {
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        titleTv.text = title
    }

    fun setToolbar(options: ToolbarOptions, displayHomeAsUpEnabled: Boolean = false) = view?.run {
        when(options) {
            is ToolbarOptions.Home -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.QrList -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.CreateCard  -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.ListCards  -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.Help -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardName -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardEmail -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardAddress -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardTelephone -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardCompany -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardNetwork -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
            is ToolbarOptions.RegisterCardColor -> bindToolbarContent(options.icon, options.title, displayHomeAsUpEnabled)
        }
    }

    private fun bindToolbarContent(icon: Int?, title: Int, displayHomeAsUpEnabled: Boolean) {
        titleTv.text = getString(title)
        toolbarIconIv.setVisible(icon != null)
        icon?.let { toolbarIconIv.setImageResource(it) }
        setToolbar(getString(title), displayHomeAsUpEnabled)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setDisplayHomeAsUpEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    fun setOrientationLandscape() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun setOrientationPortrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}
