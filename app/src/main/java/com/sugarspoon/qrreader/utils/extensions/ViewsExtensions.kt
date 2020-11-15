package com.sugarspoon.qrreader.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

fun ImageView.setQrCodeByString(
    text: String?,
    width: Int = 500,
    height: Int = 500
) {
    try {
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(
            text,
            BarcodeFormat.QR_CODE,
            width,
            height
        )
        this.setImageBitmap(bitmap)
    } catch (e: Exception) {

    }
}

fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun EditText.addTextMask(mask: String) {
    addTextChangedListener(MaskedText.applyMasked(mask, this))
}

fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>,
    layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this.context),
    decoration: RecyclerView.ItemDecoration? = null,
    hasFixedSize: Boolean = true) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    decoration?.let { this.addItemDecoration(it) }
}


