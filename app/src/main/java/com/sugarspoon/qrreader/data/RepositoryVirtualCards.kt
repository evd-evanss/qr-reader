package com.sugarspoon.qrreader.data

import android.content.Context
import androidx.room.Room
import com.sugarspoon.qrreader.data.database.QrDataBase

open class RepositoryVirtualCards(context: Context, ) {

    val db = Room.databaseBuilder(
        context,
        QrDataBase::class.java,
        NAME
    ).build()

    companion object {
        private const val NAME = "qr-reader-name"
    }
}