package com.sugarspoon.qrreader.ui.features.card.extend

import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.extensions.onCollect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VirtualCardPresenter(
    private var view: VirtualCardContract.View?,
    private var repository: VirtualCardRepository,
    private val idForSearch: Int?
) : VirtualCardContract.Presenter {

    init {
        view?.displayLoading(isLoading = true)
    }

    private var allPermissionsGranted = false

    override fun onViewCreated() {
        view?.setListeners()
    }

    override fun onAllPermissionsGranted() {
        allPermissionsGranted = true
    }

    override fun onAllPermissionsDenied() {
        allPermissionsGranted = false
    }

    override fun onViewResumed() {
        if(idForSearch != null) {
            fetchVirtualCardById()
        } else {
            fetchVirtualCard()
        }
    }

    private fun fetchVirtualCard() {
        CoroutineScope(IO).launch {
            delay(2000)
            repository.allCards.onCollect(
                onSuccess = {
                    view?.setViews(it.last())
                    view?.displayLoading(isLoading = false)
                },
                onError = {
                    view?.displayLoading(isLoading = false)
                    view?.displayError(it.message)
                }
            )
        }
    }

    private fun fetchVirtualCardById() {
        idForSearch ?: return
        CoroutineScope(IO).launch {
            delay(2000)
            val virtualCard = repository.getVirtualCardById(idForSearch)
            CoroutineScope(Main).launch {
                view?.displayLoading(false)
                view?.setViews(virtualCard = virtualCard)
            }
        }
    }

    override fun onShareClicked() {
        if(allPermissionsGranted) {
            view?.shareReceipt()
        } else {
            view?.openPreviousView()
        }
    }

    override fun onCloseClicked() {
        view?.openPreviousView()
    }

    override fun detachView() {
        view = null
    }
}
