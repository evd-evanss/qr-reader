package com.sugarspoon.qrreader.ui.features.barcode.list

import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import com.sugarspoon.qrreader.data.service.BarcodeRepository
import com.sugarspoon.qrreader.utils.extensions.onCollect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BarcodeListPresenter(
    private var view: BarcodeListContract.View?,
    private val repository: BarcodeRepository
) : BarcodeListContract.Presenter {

    override fun attachedView(view: BarcodeListFragment) {
        this.view = view
    }

    override fun onViewCreated() {
        view?.setListeners()
    }

    override fun onViewResumed() {
        view?.setViews()
        view?.displayLoading(true)
        fetchBarcodeOnDb()
    }

    private fun fetchBarcodeOnDb() {
        view?.run {
            CoroutineScope(IO).launch {
                repository.allCards.onCollect(
                    onSuccess = {
                        displayBarcodeList(it)
                    },
                    onError = {
                        displayBarcodeList(listOf())
                    }
                )
            }

        }

    }

    override fun onItemClicked(barcodeItem: BarcodeEntity) {

    }

    override fun onDeleteItem(barcodeEntity: BarcodeEntity) {
        CoroutineScope(IO).launch {
            repository.delete(barcodeEntity)
        }
    }

    override fun onOpenBrowser(barcodeEntity: BarcodeEntity) {
        view?.openBrowser(barcodeEntity)
    }

    override fun detachView() {
        view = null
    }
}
