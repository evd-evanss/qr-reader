package com.sugarspoon.qrreader.ui.features.card.card_list

import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.extensions.onCollect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CardPresenter(
    private var view: CardContract.View?,
    private val repository: VirtualCardRepository
) : CardContract.Presenter {

    override fun onViewCreated() {
        view?.setListeners()
    }

    override fun attachedView(view: CardFragment) {
        this.view = view
    }

    override fun onViewResumed() {
        view?.setViews()
        view?.displayLoading(true)
        fetchAllCards()
    }

    private fun fetchAllCards() {
        CoroutineScope(IO).launch {
            repository.allCards.onCollect(
                onSuccess = {
                    if(it.isEmpty()) {
                        view?.setBackgroundForContainer(color = R.color.lightGray)
                    } else {
                        view?.setBackgroundForContainer(color = R.color.colorAstronautBlue)
                    }
                    view?.enableRegister(enable = it.isEmpty())
                    view?.displayCards(it.toMutableList())
                    view?.displayLoading(false)
                },
                onError = {
                    view?.displayEmpty()
                    view?.displayLoading(false)
                }
            )
        }
    }

    override fun onCardAddClicked() {
        view?.openRegisterFlow()
    }

    override fun onDeleteItem(item: VirtualCardEntity) {
        CoroutineScope(IO).launch {
            repository.delete(item)
        }
    }

    override fun onExtendClicked(cardItem: VirtualCardEntity) {
        view?.openViewExtended(cardItem)
    }

    override fun detachView() {
        view = null
    }
}
