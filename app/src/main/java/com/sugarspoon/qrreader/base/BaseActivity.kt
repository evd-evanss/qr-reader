package com.sugarspoon.qrreader.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        when(options) {
            is ToolbarOptions.Home -> bindToolbarContent(options.icon, options.title)
            is ToolbarOptions.QrList -> bindToolbarContent(options.icon, options.title)
            is ToolbarOptions.CreateCard  -> bindToolbarContent(options.icon, options.title)
            is ToolbarOptions.ListCards  -> bindToolbarContent(options.icon, options.title)
            is ToolbarOptions.Help -> bindToolbarContent(options.icon, options.title)
        }
    }

    private fun bindToolbarContent(icon: Int, title: Int, ) {
        titleTv.text = getString(title)
        toolbarIconIv.setImageResource(icon)
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
