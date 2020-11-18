package com.sugarspoon.qrreader.ui.features.create_card.address

class RegisterAddressPresenter(
    private val arguments: RegisterAddressFragmentArgs
) : RegisterAddressContract.Presenter {

    private var view: RegisterAddressContract.View? = null

    override fun attachedView(view: RegisterAddressFragment) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun onContinueClicked() {
        view?.run {
            openNextStep(card = arguments.card.copy(address = address))
        }
    }

    override fun afterTextChanged(text: String) {
        view?.enableContinue(isVisible = text.length > SIZE_MIN)
    }

    override fun detachView() {
        view = null
    }

    companion object {
        private const val SIZE_MIN = 5
    }
}
