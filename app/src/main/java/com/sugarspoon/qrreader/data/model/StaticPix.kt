package com.sugarspoon.qrreader.data.model

data class StaticPix(
    val payLoadFormatIndicator: PixTemplate,
    val pointOfInitiationMethod: PixTemplate,
    val merchantAccountInfo: MerchantAccountInfo,
    val merchantCategoryCode: PixTemplate,
    val transactionCurrency: PixTemplate,
    val countryCode: PixTemplate,
    val merchantName: PixTemplate,
    val merchantCity: PixTemplate,
    val crc: PixTemplate
)

data class MerchantAccountInfo(
    val id: Int,
    val size: Int,
    val value: List<PixTemplate>
)
