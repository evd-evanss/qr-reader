package com.sugarspoon.qrreader.utils

import com.sugarspoon.qrreader.data.model.InfoPix
import com.sugarspoon.qrreader.data.model.PixTemplate

class CreatePix(private val listener: CreatePixListener) {

    private var payload = ""

    fun formatPixTemplate(infoPix: InfoPix) {
        val payLoadFormatIndicator = sequenceOf(
            PixTemplate(
                id = "00",
                size = 2,
                value = "01"
            )
        )

        val templateMerchant1 = PixTemplate(
            id = "00",
            size = 14,
            value = "0014br.gov.bcb.pix"
        )
        val templateMerchant2 = PixTemplate(
            id = "01",
            size = infoPix.key.length,
            value = "01${infoPix.key.length}${infoPix.key}"
        )
        val merchantAccountInfo = sequenceOf(
            PixTemplate(
                id = "26",
                size = (templateMerchant1.size + templateMerchant2.size) ,
                value = templateMerchant1.value+templateMerchant2.value
            )
        )
        val merchantCategoryCode = sequenceOf(
            PixTemplate(
                id = "52",
                size = 4,
                value = "0000"
            )
        )
        val transactionCurrency = sequenceOf(
            PixTemplate(
                id = "53",
                size = infoPix.value.length,
                value = infoPix.value
            )
        )
        val countryCode = sequenceOf(
            PixTemplate(
                id = "58",
                size = 2,
                value = "BR"
            )
        )
        val merchantName = sequenceOf(
            PixTemplate(
                id = "59",
                size = infoPix.name.length,
                value = infoPix.name
            )
        )
        val merchantCity = sequenceOf(
            PixTemplate(
                id = "60",
                size = infoPix.city.length,
                value = infoPix.city
            )
        )
        val additionalInfo = sequenceOf(
            PixTemplate(
                id = "62",
                size = 7,
                value = "0503***"
            )
        )
        val crc = sequenceOf(
            PixTemplate(
                id = "63",
                size = 4,
                value = "1D3D"
            )
        )
        bindPixTemplates(payLoadFormatIndicator, payload)
        bindPixTemplates(merchantAccountInfo, payload)
        bindPixTemplates(merchantCategoryCode, payload)
        bindPixTemplates(transactionCurrency, payload)
        bindPixTemplates(countryCode, payload)
        bindPixTemplates(merchantName, payload)
        bindPixTemplates(merchantCity, payload)
        bindPixTemplates(additionalInfo, payload)
        bindPixTemplates(crc, payload)
        listener.onPixCreate(payload)
    }

    private fun bindPixTemplates(pixTemplates: Sequence<PixTemplate>, pix: String) {
        pixTemplates.iterator().forEachRemaining {
            payload += it.id + it.size.toSize() + it.value
        }
    }

    fun clearPayload() {
        payload = ""
    }

    private fun Int.toSize(): String {
        var size = ""
        size = if (this < 10) {
            "0$this"
        } else {
            "$this"
        }
        return size
    }

    interface CreatePixListener{
        fun onPixCreate(payload: String)
    }

}