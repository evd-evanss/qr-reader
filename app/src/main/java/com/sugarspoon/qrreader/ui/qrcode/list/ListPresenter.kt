package com.sugarspoon.qrreader.ui.qrcode.list

import android.view.View

class ListPresenter(
    private var view: ListContract.View?
) : ListContract.Presenter {

    override fun onViewCreated() {

    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun detachView() {
        view = null
    }
}
