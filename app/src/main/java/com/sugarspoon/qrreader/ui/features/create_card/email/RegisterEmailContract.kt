package com.sugarspoon.qrreader.ui.features.create_card.email

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView

interface RegisterEmailContract {

    interface View: BaseView<Presenter> {
        val email: String
        fun setViews(): Unit?
    }

    interface Presenter: BasePresenter<View> {

    }

}