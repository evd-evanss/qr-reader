package com.sugarspoon.qrreader.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "virtual_cards_table")
data class VirtualCardEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "tel") val tel: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "site") val site: String,
    @ColumnInfo(name = "color") val color: Int,
): Serializable {
    companion object {
        fun getEmptyInstance() = VirtualCardEntity(
            id = 0,
            name = "",
            email = "",
            tel = "",
            address = "",
            company = "",
            site = "",
            color = 0,
        )
    }
}