package com.sugarspoon.qrreader.utils

import android.widget.ImageView
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