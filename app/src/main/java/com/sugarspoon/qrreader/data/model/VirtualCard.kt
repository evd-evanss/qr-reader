package com.sugarspoon.qrreader.data.model

import java.io.Serializable

data class VirtualCard(
    val name: String,
    val email: String,
    val tel: String,
    val address: String,
    val company: String,
    val site: String
): Serializable