package com.sugarspoon.qrreader.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class QrReaderMigration(val context: Context){

    init {
        //migrateTo()
    }

    private fun migrateTo() {
        Room.databaseBuilder(
            context, QrDataBase::class.java,
            "qr_database").addMigrations(migrate_1_to_2).build()
    }

    private val migrate_1_to_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `qr_code_table` (`id` INTEGER, `url` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }
}

