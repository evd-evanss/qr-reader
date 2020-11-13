package com.sugarspoon.qrreader.ui.qrcode.card

import com.sugarspoon.qrreader.data.model.VirtualCard

class VirtualCardPresenter(
    private var view: VirtualCardContract.View?,
    private var virtualCard: VirtualCard
) : VirtualCardContract.Presenter {


    override fun onViewCreated() {
        view?.setViews(virtualCard)
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun detachView() {
        view = null
    }
}
