package com.sugarspoon.qrreader.ui.features.card_list

import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.extensions.onCollect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CardListPresenter(
    private var view: CardListContract.View?,
    private val repository: VirtualCardRepository
) : CardListContract.Presenter {

    override fun onViewCreated() {
        view?.setListeners()
    }

    override fun attachedView(view: CardListFragment) {
        this.view = view
    }

    override fun onViewResumed() {
        view?.setViews()
        fetchAllCards()
    }

    private fun fetchAllCards() {
        CoroutineScope(IO).launch {
            repository.allCards.onCollect(
                onSuccess = {
                    view?.displayCards(it.toMutableList())
                },
                onError = {
                    view?.displayError(it.message)
                }
            )

        }
    }

    override fun onCardClicked(cardItem: VirtualCardEntity) {
        view?.openShareHelper(cardItem)
    }

    override fun detachView() {
        view = null
    }
}
