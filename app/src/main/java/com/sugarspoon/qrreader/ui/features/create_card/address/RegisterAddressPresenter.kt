package com.sugarspoon.qrreader.ui.features.create_card.address

class RegisterAddressPresenter(
    private var view: RegisterAddressContract.View?,
) : RegisterAddressContract.Presenter {

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    private fun allFieldValid() : Boolean {
        view?.run {
            return if(address.isEmpty()) {
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
