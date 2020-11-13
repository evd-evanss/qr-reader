package com.sugarspoon.qrreader.ui.qrcode.reader

import android.view.View
import com.sugarspoon.qrreader.data.ResultQr

class ReaderPresenter(
    private var view: ReaderContract.View?
) : ReaderContract.Presenter {

    private var url: String? = null

    override fun onViewCreated() {
        view?.setViews(ResultQr)
        url = ResultQr.text
    }

    override fun onViewResumed() {
        view?.setListeners()
    }

    override fun onWebSiteClicked() {
        view?.displayAlert()
    }

    override fun onConfirmDialogClicked() {
        view?.openWebSite(url)
    }

    override fun detachView() {
        view = null
    }
}
