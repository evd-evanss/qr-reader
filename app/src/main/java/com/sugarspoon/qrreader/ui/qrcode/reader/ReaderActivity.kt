package com.sugarspoon.qrreader.ui.qrcode.reader

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import com.sugarspoon.qrreader.data.ResultQr
import com.sugarspoon.qrreader.utils.setQrCodeByString
import com.sugarspoon.qrreader.widgets.GenericDialog
import kotlinx.android.synthetic.main.activity_reader.*


class ReaderActivity : BaseActivity(), ReaderContract.View {

    private val presenter: ReaderContract.Presenter by lazy {
        val presenter = ReaderPresenter(this)
        presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setListeners() {
        visitWebsiteBt.setOnClickListener {
            presenter.onWebSiteClicked()
        }
    }

    override fun setViews(qrCode: ResultQr) {
        setToolbar(getString(R.string.app_name), false)
        readerContentIv.setQrCodeByString(qrCode.text)
        qrCodeTextTv.text = qrCode.text
    }

    override fun displayAlert() {
        GenericDialog(
            context = this,
            title = R.string.generic_dialog_title,
            body = R.string.generic_dialog_body,
            listener = object : GenericDialog.GenericDialogListener {
                override fun onConfirm() {
                    presenter.onConfirmDialogClicked()
                }
                override fun onCancel() {}
            }
        ).show()
    }

    override fun openWebSite(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun finalize() {
        finish()
    }
}

