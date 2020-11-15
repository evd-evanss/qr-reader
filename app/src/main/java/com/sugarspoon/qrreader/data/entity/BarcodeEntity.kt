package com.sugarspoon.qrreader.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_code_table")
class BarcodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "date") val date: String,
)