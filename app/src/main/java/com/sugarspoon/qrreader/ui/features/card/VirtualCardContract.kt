package com.sugarspoon.qrreader.ui.features.card

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface VirtualCardContract {

    interface View: BaseView<Presenter> {
        fun setViews(virtualCard: VirtualCardEntity)
        fun displayLoading(isLoading: Boolean)
        fun displayError(message: String?)
        fun shareReceipt()
    }

    interface Presenter: BasePresenter<View> {
        fun onShareClicked()
    }

}