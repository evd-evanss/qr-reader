package com.sugarspoon.qrreader.widgets

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.extensions.setVisible
import kotlinx.android.synthetic.main.generic_dialog.*

class GenericDialog(
    context: Context,
    private val title: Int,
    private val body: Int,
    private val confirmText: Int? = R.string.action_continue,
    private val cancelText: Int? = R.string.action_cancel,
    private val listener: GenericDialogListener? = null
): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generic_dialog)
        setupUi()
        setupListeners()
    }

    fun showIfPermissionsGranted(isGranted: Boolean) {
        if(!isGranted) this.show()
    }

    private fun setupUi() {
        genericDialogTitleTv.text = context.getString(title)
        genericDialogBodyTv.text = context.getString(body)
        genericDialogConfirmBt.setVisible(confirmText != null)
        genericDialogCancelBt.setVisible(cancelText != null)
        confirmText?.let {
            genericDialogConfirmBt.text = context.getString(it)
        }
        cancelText?.let {
            genericDialogCancelBt.text = context.getString(it)
        }
    }

    private fun setupListeners() {
        genericDialogConfirmBt.setOnClickListener {
            listener?.onConfirm()
            this.dismiss()
        }
        genericDialogCancelBt.setOnClickListener {
            listener?.onCancel()
            this.dismiss()
        }
    }

    interface GenericDialogListener{
        fun onConfirm()
        fun onCancel()
    }
}