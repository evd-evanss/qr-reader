package com.sugarspoon.qrreader.ui.features.create_card.email

class RegisterEmailPresenter(
    private var view: RegisterEmailContract.View?,
) : RegisterEmailContract.Presenter {

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    private fun allFieldValid() : Boolean {
        view?.run {
            return if(email.isEmpty()) {
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
