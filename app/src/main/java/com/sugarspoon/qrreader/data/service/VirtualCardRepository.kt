package com.sugarspoon.qrreader.data.service

import androidx.annotation.WorkerThread
import com.sugarspoon.qrreader.data.dao.VirtualCardDao
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import kotlinx.coroutines.flow.Flow

class VirtualCardRepository(private val virtualCardDao: VirtualCardDao) {

    val allCards: Flow<List<VirtualCardEntity>> = virtualCardDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(card: VirtualCardEntity) {
        virtualCardDao.insertAll(card)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(card: VirtualCardEntity) {
        virtualCardDao.delete(card)
    }
}