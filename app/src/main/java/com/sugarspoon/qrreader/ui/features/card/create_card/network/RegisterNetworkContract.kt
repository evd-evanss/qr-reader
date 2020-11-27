package com.sugarspoon.qrreader.ui.features.card.create_card.network

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterNetworkContract {

    interface View: BaseView<Presenter> {
        val site: String
        fun setViews(): Unit?
        fun enableContinue(isVisible: Boolean)
        fun openNextStep(card: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun afterTextChanged(text: String)
        fun onContinueClicked()
        fun attachedView(view: RegisterNetworkFragment)
    }

}