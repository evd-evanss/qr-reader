package com.sugarspoon.qrreader.ui.features.card

import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.WindowManager
import com.google.zxing.integration.android.IntentIntegrator
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.PermissionsHelper
import com.sugarspoon.qrreader.utils.Screenshot
import com.sugarspoon.qrreader.utils.extensions.setQrCodeByString
import com.sugarspoon.qrreader.widgets.ShareReceiptHelper
import kotlinx.android.synthetic.main.activity_virtual_card.*
import kotlinx.android.synthetic.main.item_cards.view.*
import org.jetbrains.anko.toast

class VirtualCardActivity :
    BaseActivity(),
    CreatePix.CreatePixListener,
    PermissionsHelper.OnPermissionResult,
    VirtualCardContract.View{

    private val presenter: VirtualCardContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(this)
        val repository = VirtualCardRepository(database.virtualCardDao())
        val idForSearch = intent.extras?.getInt(KEY)
        val presenter = VirtualCardPresenter(this, repository, idForSearch)
        presenter
    }

    private val shareReceiptHelper: ShareReceiptHelper by lazy {
        ShareReceiptHelper(this, receiptVirtualCard)
    }

    private val generatePix by lazy {
        CreatePix(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_virtual_card)
        virtualCardLoadingLv.setView()
        presenter.onViewCreated()
        setOrientationLandscape()
    }

    override fun setViews(virtualCard: VirtualCardEntity) {
        view?.run {
            virtualCardTitleTv.text = virtualCard.company
            virtualCardName.text = virtualCard.name
            virtualCardTel.text = virtualCard.tel
            virtualCardEmail.text = virtualCard.email
            virtualCardAddress.text = virtualCard.address
            virtualCardSite.text = virtualCard.site
            readerContentIv.setQrCodeByString(virtualCard.site)
            receiptVirtualCard.background.setColorFilter(context.getColor(virtualCard.color), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun displayLoading(isLoading: Boolean) {
        virtualCardLoadingLv.displayLoading(isLoading)
    }

    override fun displayError(message: String?) {
        toast(message ?: "Erro desconhecido").show()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setListeners() {
        view?.run {
            virtualCardShareIv.setOnClickListener {
                presenter.onShareClicked()
            }
        }
    }

    override fun onPixCreate(payload: String) {
        generatePix.clearPayload()
    }

    override fun shareReceipt() {
        Screenshot.takeScreenshot(receiptVirtualCard)
        //shareReceiptHelper.shareReceipt()
    }

    override fun onAllPermissionsGranted(requestCode: Int) {

    }

    override fun onPermissionsDenied(
        requestCode: Int,
        deniedPermissions: List<String>,
        deniedPermissionsWithNeverAskAgainOption: List<String>
    ) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        shareReceiptHelper.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            IntentIntegrator.parseActivityResult(
                requestCode,
                resultCode,
                data
            )
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    companion object {
        private const val KEY = "id"
    }
}