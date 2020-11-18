package com.sugarspoon.qrreader.ui.features.create_card.color

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface RegisterColorContract {

    interface View: BaseView<Presenter> {
        fun setViews(): Unit?
        fun displayCardCreated(cardEntity: VirtualCardEntity)
        fun displayLoading(isLoading: Boolean)
    }

    interface Presenter: BasePresenter<View> {
        fun attachedView(view: RegisterColorFragment)
        fun onContinueClicked()
        fun chooseColor(color: Int)
    }

}