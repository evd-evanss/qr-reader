package com.sugarspoon.qrreader.base

import android.view.MenuItem
import com.sugarspoon.qrreader.extensions.showToast
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

abstract class BaseFragment : androidx.fragment.app.Fragment() {

    protected val TAG =
        if (javaClass.enclosingClass != null) javaClass.enclosingClass?.simpleName else javaClass.simpleName

    //TOAST METHODS
    fun showToast(string: String) {
        activity?.showToast(string)
    }

    //TOOLBAR METHODS
    fun setToolbar(title: String, displayHomeAsUpEnabled: Boolean) {
        (activity as BaseActivity).setToolbar(title, displayHomeAsUpEnabled)
        view?.run {
            titleTv.text = title
        }
    }

    fun setToolbar(options: ToolbarOptions, displayHomeAsUpEnabled: Boolean = false) = view?.run {
        (activity as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
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

    fun setOrientationLandscape() {
        (activity as BaseActivity).setOrientationLandscape()
    }

    fun setOrientationPortrait() {
        (activity as BaseActivity).setOrientationPortrait()
    }

    //MENU METHODS
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
