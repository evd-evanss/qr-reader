package com.sugarspoon.qrreader.ui.features.create_card.network

import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

class RegisterNetworkPresenter(
    private val arguments: RegisterNetworkFragmentArgs) :  RegisterNetworkContract.Presenter {

    private var view: RegisterNetworkContract.View? = null

    override fun attachedView(view: RegisterNetworkFragment) {
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
            enableContinue(isVisible = text.length > IS_NETWORK_VALID)
        }
    }

    override fun onContinueClicked() {
        view?.run {
            openNextStep(arguments.card.copy(site = site))
        }
    }

    override fun detachView() {
        view = null
    }

    companion object {
        private const val IS_NETWORK_VALID = 2
    }
}
