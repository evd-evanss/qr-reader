package com.sugarspoon.qrreader.ui.features.card.create_card.company

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterCompanyContract {

    interface View: BaseView<Presenter> {
        val company: String
        fun setViews(): Unit?
        fun enableContinue(isVisible: Boolean)
        fun openNextStep(card: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun attachedView(view: RegisterCompanyFragment)
        fun afterTextChanged(text: String)
        fun onContinueClicked()
    }

}