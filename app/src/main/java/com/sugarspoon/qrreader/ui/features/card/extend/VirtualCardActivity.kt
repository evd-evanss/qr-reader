package com.sugarspoon.qrreader.ui.features.card.extend

import android.Manifest
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
import com.sugarspoon.qrreader.utils.PermissionsHelper
import com.sugarspoon.qrreader.utils.Screenshot
import com.sugarspoon.qrreader.utils.extensions.setQrCodeByString
import com.sugarspoon.qrreader.utils.extensions.setVisible
import kotlinx.android.synthetic.main.activity_virtual_card.*
import kotlinx.android.synthetic.main.item_cards.view.*
import org.jetbrains.anko.toast

class VirtualCardActivity :
    BaseActivity(),
    PermissionsHelper.OnPermissionResult,
    VirtualCardContract.View {

    private val presenter: VirtualCardContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(this)
        val repository = VirtualCardRepository(database.virtualCardDao())
        val idForSearch = intent.extras?.getInt(KEY)
        val presenter = VirtualCardPresenter(this, repository, idForSearch)
        presenter
    }

    private val permissionsHelper by lazy {
        PermissionsHelper(
            this,
            REQUEST_CODE,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            this,
        )
    }

    private val screenShot by lazy {
        Screenshot(
            this,
            onSuccess = {
                finish()
            },
            onFail = {
                toast("Erro desconhecido")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
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
            receiptVirtualCard.background.setColorFilter(
                context.getColor(virtualCard.color),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    override fun displayLoading(isLoading: Boolean) {
        virtualCardLoadingLv.displayLoading(isLoading)
        virtualCardContainer.setVisible(!isLoading)
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
                permissionsHelper.dispatchPermissions()
                presenter.onShareClicked()
            }
            virtualCardCloseIv.setOnClickListener {
                presenter.onCloseClicked()
            }
        }
    }

    override fun shareReceipt() {
        virtualCardShareIv.setVisible(visible = false)
        virtualCardCloseIv.setVisible(visible = false)
        screenShot.takeAndShare(receiptVirtualCard)
    }

    override fun openPreviousView() {
        onBackPressed()
    }

    override fun onAllPermissionsGranted(requestCode: Int) {
        presenter.onAllPermissionsGranted()
    }

    override fun onPermissionsDenied(
        requestCode: Int,
        deniedPermissions: List<String>,
        deniedPermissionsWithNeverAskAgainOption: List<String>
    ) {
        presenter.onAllPermissionsDenied()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsHelper.onRequestPermissionsResult(
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    companion object {
        private const val KEY = "id"
        private const val REQUEST_CODE = 200
    }
}