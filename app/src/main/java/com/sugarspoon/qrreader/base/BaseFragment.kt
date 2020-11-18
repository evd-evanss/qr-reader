package com.sugarspoon.qrreader.base

import android.view.MenuItem
import com.sugarspoon.qrreader.extensions.setVisible
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
        (activity as BaseActivity).setToolbar(getString(title), displayHomeAsUpEnabled)
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
