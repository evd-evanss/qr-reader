package com.sugarspoon.qrreader.ui.features.create_card.address

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView

interface RegisterAddressContract {

    interface View: BaseView<Presenter> {
        val address: String
        fun setViews(): Unit?
    }

    interface Presenter: BasePresenter<View> {

    }

}