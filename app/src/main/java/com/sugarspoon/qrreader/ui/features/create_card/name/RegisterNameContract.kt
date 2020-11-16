package com.sugarspoon.qrreader.ui.features.create_card.name

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView

interface RegisterNameContract {

    interface View: BaseView<Presenter> {
        val name: String
        fun setViews(): Unit?
    }

    interface Presenter: BasePresenter<View> {

    }

}