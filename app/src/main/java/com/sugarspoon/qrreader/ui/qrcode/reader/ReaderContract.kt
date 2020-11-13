package com.sugarspoon.qrreader.ui.qrcode.reader

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.ResultQr

interface ReaderContract {

    interface View: BaseView<Presenter> {
        fun setViews(qrCode: ResultQr)
        fun displayAlert()
        fun openWebSite(url: String?)
        fun finalize()
    }

    interface Presenter: BasePresenter<View> {
        fun onWebSiteClicked()
        fun onConfirmDialogClicked()
    }

}