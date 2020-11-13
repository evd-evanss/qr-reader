package com.sugarspoon.qrreader.ui.qrcode.create

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.model.VirtualCard

interface CreateContract {

    interface View: BaseView<Presenter> {
        val name: String
        val email: String
        val tel: String
        val address: String
        val company: String
        val site: String
        fun setViews(): Unit?
        fun generateVirtualCard(virtualCard: VirtualCard)
        fun displayFieldError()
    }

    interface Presenter: BasePresenter<View> {
        fun onGenerateVirtualCardClicked()

    }

}