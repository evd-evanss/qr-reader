package com.sugarspoon.qrreader.ui.features.create_card.email

class RegisterEmailPresenter(
    private val arguments: RegisterEmailFragmentArgs,
) : RegisterEmailContract.Presenter {

    private var view: RegisterEmailContract.View? = null

    override fun attachedView(view: RegisterEmailFragment) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun onContinueClicked() {
        view?.openNextStep(
            arguments.card.copy(
                email = view?.email ?: ""
            )
        )
    }

    override fun afterTextChanged(validEmail: Boolean) {
        view?.run {
            view?.enableContinue(isEnable = validEmail)
        }
    }

    override fun detachView() {
        view = null
    }
}
