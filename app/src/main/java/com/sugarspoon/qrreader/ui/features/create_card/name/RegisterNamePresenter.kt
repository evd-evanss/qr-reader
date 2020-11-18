package com.sugarspoon.qrreader.ui.features.create_card.name

import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

class RegisterNamePresenter : RegisterNameContract.Presenter {

    private var view: RegisterNameContract.View? = null

    override fun attachedView(view: RegisterNameContract.View) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun onContinueClicked() {
        if(allFieldValid()) {
            view?.nextStep(
                VirtualCardEntity.getEmptyInstance().copy(
                    name = view?.name ?: ""
                )
            )
        }
    }

    override fun afterTextChanged(text: String) {
        view?.enableContinue(isEnable = text.length > 7)
    }

    private fun allFieldValid() : Boolean {
        view?.run {
            return name.isNotEmpty() && name.length > 7
        }
        return false
    }

    override fun detachView() {
        //view = null
    }
}
