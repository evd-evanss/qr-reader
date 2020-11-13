package com.sugarspoon.qrreader.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sugarspoon.qrreader.data.dao.VirtualCardDao
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(VirtualCardEntity::class), version = 1, exportSchema = false)
abstract class QrDataBase : RoomDatabase() {

    abstract fun virtualCardDao(): VirtualCardDao

    companion object {
        @Volatile
        private var INSTANCE: QrDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): QrDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QrDataBase::class.java,
                    "qr_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        var dao = database.virtualCardDao()
                        populateDatabase(database.virtualCardDao())
                    }
                }
            }

            suspend fun populateDatabase(wordDao: VirtualCardDao) {
            }
        }
    }
}