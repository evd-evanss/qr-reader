package com.sugarspoon.qrreader.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.formatString(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date())
}