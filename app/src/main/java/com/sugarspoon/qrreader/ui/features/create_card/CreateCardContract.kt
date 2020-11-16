package com.sugarspoon.qrreader.ui.features.create_card

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface CreateCardContract {

    interface View: BaseView<Presenter> {
        val name: String
        val email: String
        val tel: String
        val address: String
        val company: String
        val site: String
        fun setViews(): Unit?
        fun generateVirtualCard(virtualCard: VirtualCardEntity)
        fun displayFieldError()
        fun chooseCardColor(color: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun onGenerateVirtualCardClicked()
        fun chooseColorRed()
        fun chooseColorBlue()
        fun chooseColorGreen()

    }

}