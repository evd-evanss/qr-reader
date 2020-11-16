package com.sugarspoon.qrreader.ui.features.barcode_list

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.BarcodeEntity

interface BarcodeListContract {

    interface View: BaseView<Presenter> {
        fun setViews()
        fun displayError(message: String?)
        fun openBrowser(barcodeItem: BarcodeEntity)
        fun displayBarcodeList(list: List<BarcodeEntity>)
        fun displayLoading(isLoading: Boolean)
    }

    interface Presenter: BasePresenter<View> {
        fun attachedView(view: BarcodeListFragment)
        fun onItemClicked(barcodeItem: BarcodeEntity)
        fun onDeleteItem(barcodeEntity: BarcodeEntity)
        fun onOpenBrowser(barcodeEntity: BarcodeEntity)
    }

}