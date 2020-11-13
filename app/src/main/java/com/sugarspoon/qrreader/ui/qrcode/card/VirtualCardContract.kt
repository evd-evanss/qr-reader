package com.sugarspoon.qrreader.ui.qrcode.card

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.model.VirtualCard

interface VirtualCardContract {

    interface View: BaseView<Presenter> {
        fun setViews(virtualCard: VirtualCard)
    }

    interface Presenter: BasePresenter<View> {
    }

}