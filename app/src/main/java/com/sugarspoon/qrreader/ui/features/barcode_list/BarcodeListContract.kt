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
        fun displayPlaceHolder()
    }

    interface Presenter: BasePresenter<View> {
        fun onItemClicked(barcodeItem: BarcodeEntity)
        fun attachedView(view: BarcodeListFragment)
    }

}