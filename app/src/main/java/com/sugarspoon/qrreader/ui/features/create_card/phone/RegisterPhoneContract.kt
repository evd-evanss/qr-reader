package com.sugarspoon.qrreader.ui.features.create_card.phone

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterPhoneContract {

    interface View: BaseView<Presenter> {
        val phone: String?
        fun setViews(): Unit?
        fun enableContinue(isVisible: Boolean)
        fun openNextStep(cardEntity: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun afterTextChanged(text: String)
        fun onContinueClicked()
        fun attachedView(view: RegisterPhoneFragment)
    }

}