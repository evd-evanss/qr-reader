package com.sugarspoon.qrreader.ui.features.card

import android.os.Bundle
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.extensions.setQrCodeByString
import com.sugarspoon.qrreader.widgets.ShareReceiptHelper
import kotlinx.android.synthetic.main.activity_virtual_card.*
import org.jetbrains.anko.toast

class VirtualCardActivity :
    BaseActivity(),
    CreatePix.CreatePixListener,
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
        shareReceiptHelper.shareReceipt()
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