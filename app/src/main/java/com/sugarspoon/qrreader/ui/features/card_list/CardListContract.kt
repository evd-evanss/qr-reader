package com.sugarspoon.qrreader.ui.features.card_list

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface CardListContract {

    interface View: BaseView<Presenter> {
        fun setViews()
        fun displayCards(cardList: MutableList<VirtualCardEntity>)
        fun displayError(message: String?)
        fun openShareHelper(content: VirtualCardEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun onCardClicked(cardItem: VirtualCardEntity)
        fun attachedView(view: CardListFragment)

    }

}