package com.sugarspoon.qrreader.ui.features.card.create_card.name

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterNameContract {

    interface View: BaseView<Presenter> {
        val name: String
        fun setViews(): Unit
        fun nextStep(virtualCardEntity: VirtualCardEntity)
        fun enableContinue(isEnable: Boolean)
    }

    interface Presenter: BasePresenter<View> {
        fun attachedView(view: View)
        fun afterTextChanged(text: String)
        fun onContinueClicked()
    }

}