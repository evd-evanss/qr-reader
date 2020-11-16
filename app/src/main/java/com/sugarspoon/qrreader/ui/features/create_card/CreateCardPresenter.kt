package com.sugarspoon.qrreader.ui.features.create_card

import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository

class CreateCardPresenter(
    private var view: CreateCardContract.View?,
    private val repository: VirtualCardRepository
) : CreateCardContract.Presenter {

    private var colorChosen = GREEN

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

    override fun chooseColorRed() {
        view?.chooseCardColor(color = RED)
        colorChosen = RED
    }

    override fun chooseColorBlue() {
        view?.chooseCardColor(color = BLUE)
        colorChosen = BLUE
    }

    override fun chooseColorGreen() {
        view?.chooseCardColor(color = GREEN)
        colorChosen = GREEN
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
                company = company,
                color = colorChosen
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

    companion object {
        private const val RED = R.color.red
        private const val BLUE = R.color.color_blue_dark
        private const val GREEN = R.color.green_mid
    }
}
