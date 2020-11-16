package com.sugarspoon.qrreader.ui.features.create_card.name

class RegisterNamePresenter(
    private var view: RegisterNameContract.View?,
) : RegisterNameContract.Presenter {

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    private fun allFieldValid() : Boolean {
        view?.run {
            return if(name.isEmpty()) {
//                view?.displayFieldError()
                false
            } else {
                true
            }
        }
        return false
    }

    override fun detachView() {
        view = null
    }
}
