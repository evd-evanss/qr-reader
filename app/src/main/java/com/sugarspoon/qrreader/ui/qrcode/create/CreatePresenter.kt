package com.sugarspoon.qrreader.ui.qrcode.create

import android.view.View
import com.sugarspoon.qrreader.data.model.VirtualCard

class CreatePresenter(
    private var view: CreateContract.View?
) : CreateContract.Presenter {

    override fun onViewCreated() {
        view?.setViews()
    }

    override fun onViewResumed() {
       view?.setListeners()
    }

    override fun onGenerateVirtualCardClicked() {
        view?.run {
            if(allFieldValid()) {
                generateVirtualCard(
                    VirtualCard(
                        name = name,
                        email = email,
                        tel = tel,
                        address = address,
                        site = site,
                        company = company
                    )
                )
            }
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
