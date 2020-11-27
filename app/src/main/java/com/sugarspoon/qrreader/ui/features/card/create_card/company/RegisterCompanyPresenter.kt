package com.sugarspoon.qrreader.ui.features.card.create_card.company

class RegisterCompanyPresenter(
    private val arguments: RegisterCompanyFragmentArgs) : RegisterCompanyContract.Presenter {

    private var view: RegisterCompanyContract.View? = null

    override fun attachedView(view: RegisterCompanyFragment) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun afterTextChanged(text: String) {
        view?.run {
            enableContinue(isVisible = text.length > IS_COMPANY_VALID)
        }
    }

    override fun onContinueClicked() {
        view?.run {
            openNextStep(card = arguments.card.copy(company = company))
        }
    }

    override fun detachView() {
        view = null
    }

    companion object {
        private const val IS_COMPANY_VALID = 5
    }
}
