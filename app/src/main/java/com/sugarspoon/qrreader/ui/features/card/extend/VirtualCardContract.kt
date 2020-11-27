package com.sugarspoon.qrreader.ui.features.card.extend

import com.sugarspoon.qrreader.base.BasePresenter
import com.sugarspoon.qrreader.base.BaseView
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity

interface VirtualCardContract {

    interface View: BaseView<Presenter> {
        fun setViews(virtualCard: VirtualCardEntity)
        fun displayLoading(isLoading: Boolean)
        fun displayError(message: String?)
        fun shareReceipt()
        fun openPreviousView()
    }

    interface Presenter: BasePresenter<View> {
        fun onShareClicked()
        fun onCloseClicked()
        fun onAllPermissionsGranted()
        fun onAllPermissionsDenied()
    }

}