package com.sugarspoon.qrreader.ui.features.card.card_list

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface CardContract {

    interface View: BaseView<Presenter> {
        fun setViews()
        fun displayCards(cardList: MutableList<VirtualCardEntity>)
        fun displayEmpty()
        fun openViewExtended(content: VirtualCardEntity)
        fun displayLoading(isLoading: Boolean)
        fun openRegisterFlow()
        fun enableRegister(enable: Boolean)
        fun setBackgroundForContainer(color: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun onExtendClicked(cardItem: VirtualCardEntity)
        fun attachedView(view: CardFragment)
        fun onCardAddClicked()
        fun onDeleteItem(item: VirtualCardEntity)

    }

}