package com.sugarspoon.qrreader.base

import android.view.MenuItem
import com.sugarspoon.qrreader.extensions.showToast
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
