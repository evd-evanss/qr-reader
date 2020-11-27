package com.sugarspoon.qrreader.ui.features.card.create_card.color

import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.extensions.onCollect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterColorPresenter(
    private val arguments: RegisterColorFragmentArgs,
    private val repository: VirtualCardRepository
) : RegisterColorContract.Presenter {

    private var view: RegisterColorContract.View? = null
    private var color: Int = R.color.green_mid

    override fun attachedView(view: RegisterColorFragment) {
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
            displayLoading(isLoading = true)
            repository.insert(
                arguments.card.copy(
                    color = color
                )
            )
            getCardGenerated()
        }
    }

    private fun getCardGenerated() {
        CoroutineScope(IO).launch {
            delay(1000)
            repository.allCards.onCollect(
                onSuccess = {
                    view?.run {
                        displayCardCreated(it.first())
                        displayLoading(isLoading = false)
                    }
                },
                onError = {
                    view?.run {
                        displayLoading(isLoading = false)
                    }
                }
            )
        }
    }

    override fun chooseColor(color: Int) {
        this.color = color
    }

    override fun detachView() {
        view = null
    }
}
