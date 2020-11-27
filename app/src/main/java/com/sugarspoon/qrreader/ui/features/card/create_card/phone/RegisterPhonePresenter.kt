package com.sugarspoon.qrreader.ui.features.card.create_card.phone

class RegisterPhonePresenter(
    private val arguments: RegisterPhoneFragmentArgs
) : RegisterPhoneContract.Presenter {

    private var view: RegisterPhoneContract.View? = null

    override fun attachedView(view: RegisterPhoneFragment) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun afterTextChanged(text: String) {
        view?.enableContinue(isVisible = text.length > IS_PHONE_VALID)
    }

    override fun onContinueClicked() {
        view?.run {
            openNextStep(arguments.card.copy(tel = phone ?: ""))
        }
    }

    override fun detachView() {
        view = null
    }

    companion object {
        private const val IS_PHONE_VALID = 10
    }
}
