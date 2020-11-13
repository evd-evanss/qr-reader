package com.sugarspoon.qrreader.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "virtual_cards_table")
data class VirtualCardEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "tel") val tel: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "site") val site: String
)