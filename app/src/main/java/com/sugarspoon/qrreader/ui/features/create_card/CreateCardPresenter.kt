package com.sugarspoon.qrreader.ui.features.create_card

import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository

class CreateCardPresenter(
    private var view: CreateCardContract.View?,
    private val repository: VirtualCardRepository
) : CreateCardContract.Presenter {

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun onGenerateVirtualCardClicked() {
        view?.run {
            if(allFieldValid()) {
                saveVirtualCard()
            }
        }
    }

    private fun saveVirtualCard() {
        view?.run {
            val virtualCard = VirtualCardEntity(
                id = 0,
                name = name,
                email = email,
                tel = tel,
                address = address,
                site = site,
                company = company
            )
            repository.insert(virtualCard)
            generateVirtualCard(virtualCard = virtualCard)
        }

    }

    private fun allFieldValid() : Boolean {
        view?.run {
            return if(name.isEmpty() || email.isEmpty() || site.isEmpty() || address.isEmpty()) {
                view?.displayFieldError()
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
