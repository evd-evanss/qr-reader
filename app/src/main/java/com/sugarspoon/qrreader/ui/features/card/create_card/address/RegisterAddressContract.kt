package com.sugarspoon.qrreader.ui.features.card.create_card.address

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterAddressContract {

    interface View: BaseView<Presenter> {
        val address: String
        fun setViews(): Unit?
        fun enableContinue(isVisible: Boolean)
        fun openNextStep(card: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun onContinueClicked()
        fun afterTextChanged(text: String)
        fun attachedView(view: RegisterAddressFragment)

    }

}