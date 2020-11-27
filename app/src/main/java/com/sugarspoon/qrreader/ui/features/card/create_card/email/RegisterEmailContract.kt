package com.sugarspoon.qrreader.ui.features.card.create_card.email

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterEmailContract {

    interface View: BaseView<Presenter> {
        val email: String
        fun setViews(): Unit?
        fun enableContinue(isEnable: Boolean)
        fun openNextStep(virtualCardEntity: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun attachedView(view: RegisterEmailFragment)
        fun onContinueClicked()
        fun afterTextChanged(validEmail: Boolean)
    }

}