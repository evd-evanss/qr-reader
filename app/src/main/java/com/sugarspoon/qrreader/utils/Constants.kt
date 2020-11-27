package com.sugarspoon.qrreader.utils

import com.sugarspoon.qrreader.BuildConfig

object Constants {
    val PACKAGE_NAME: String = BuildConfig.APPLICATION_ID
    val AUTHORITY: String = PACKAGE_NAME + ".fileprovider"
}