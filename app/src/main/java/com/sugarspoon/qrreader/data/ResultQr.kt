package com.sugarspoon.qrreader.data

import android.graphics.Bitmap
import com.journeyapps.barcodescanner.BarcodeResult

object ResultQr {
    var bitmap: Bitmap? = null
    var text: String? = null
    var timestamp: Long? = null

    fun setResult(result: BarcodeResult) {
        result.run {
            ResultQr.bitmap = bitmap
            ResultQr.text = text
            ResultQr.timestamp = timestamp
        }
    }

    fun clearResult() {
        bitmap = null
        text = null
        timestamp = null
    }
}