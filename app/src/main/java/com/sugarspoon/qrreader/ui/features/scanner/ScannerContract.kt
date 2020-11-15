package com.sugarspoon.qrreader.ui.features.scanner

import com.journeyapps.barcodescanner.BarcodeResult
import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView

interface ScannerContract {

    interface View: BaseView<Presenter> {
        fun setViews()
        fun dispatcherPermissions()
        fun displayError()
        fun displayResult(result: BarcodeResult)
        fun finalize()
    }

    interface Presenter: BasePresenter<View> {
        fun onBarcodeResult(result: BarcodeResult?)
        fun onPermissionsGranted()
        fun onPermissionsDenied()
        fun onViewAtached(view: ScannerFragment)
    }

}