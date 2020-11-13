package com.sugarspoon.qrreader.ui.qrcode.scanner

import com.journeyapps.barcodescanner.BarcodeResult

class ScannerPresenter(
    private var view: ScannerContract.View?
) : ScannerContract.Presenter {

    private var permissionsGranted = false

    override fun onViewAtached(view: ScannerFragment) {
        this.view = view
        onViewCreated()
    }

    override fun onViewCreated() {
        view?.setViews()
        fetchPermissions()
    }

    private fun fetchPermissions() {
        view?.dispatcherPermissions()
    }

    override fun onViewResumed() {
        fetchPermissions()
        if(permissionsGranted) view?.setListeners()
    }

    override fun onPermissionsGranted() {
        permissionsGranted = true
        view?.setListeners()
    }

    override fun onPermissionsDenied() {
        permissionsGranted = false
        view?.finalize()
    }

    override fun onBarcodeResult(result: BarcodeResult?) {
        result?.run {
            view?.displayResult(result)
        }
    }

    override fun detachView() {
        view = null
    }
}
