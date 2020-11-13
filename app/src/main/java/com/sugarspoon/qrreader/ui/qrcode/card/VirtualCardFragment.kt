package com.sugarspoon.qrreader.ui.qrcode.card

import android.os.Bundle
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import com.sugarspoon.qrreader.data.model.VirtualCard
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.setQrCodeByString
import kotlinx.android.synthetic.main.fragment_virtual_card.view.*


class VirtualCardFragment :
    BaseActivity(),
    CreatePix.CreatePixListener,
    VirtualCardContract.View{

    private val presenter: VirtualCardContract.Presenter by lazy {
        val intent = this.intent
        val virtualCard= intent.extras?.getSerializable("virtualCard") as VirtualCard
        val presenter = VirtualCardPresenter(this, virtualCard)
        presenter
    }

    private val generatePix by lazy {
        CreatePix(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_virtual_card)
        presenter.onViewCreated()
        setOrientationLandscape()
    }

    override fun setViews(virtualCard: VirtualCard) {
        view?.run {
            virtualCardTitleTv.text = virtualCard.company
            virtualCardTel.text = virtualCard.tel
            virtualCardEmail.text = virtualCard.email
            virtualCardAddress.text = virtualCard.address
            virtualCardSite.text = virtualCard.site
            readerContentIv.setQrCodeByString(virtualCard.site)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setListeners() {
        view?.run {

        }
    }

    override fun onPixCreate(payload: String) {
        generatePix.clearPayload()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}