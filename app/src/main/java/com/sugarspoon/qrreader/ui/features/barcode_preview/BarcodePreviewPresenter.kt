package com.sugarspoon.qrreader.ui.features.barcode_preview

import com.sugarspoon.qrreader.data.ResultQr
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import com.sugarspoon.qrreader.data.service.BarcodeRepository
import com.sugarspoon.qrreader.utils.extensions.formatString
import java.util.*

class BarcodePreviewPresenter(
    private var view: BarcodePreviewContract.View?,
    private val repository: BarcodeRepository
) : BarcodePreviewContract.Presenter {

    private var url: String? = null

    override fun onViewCreated() {
        view?.setListeners()
    }

    override fun onViewResumed() {
        view?.setViews(ResultQr)
        url = ResultQr.text
        val barcodeUrl = url ?: return
        repository.insert(
            BarcodeEntity(
                id = 0,
                url= barcodeUrl,
                date = Date().formatString()
            )
        )
    }

    override fun onWebSiteClicked() {
        view?.displayAlert()
    }

    override fun onConfirmDialogClicked() {
        view?.openWebSite(url)
    }

    override fun detachView() {
        view = null
    }
}
