package com.sugarspoon.qrreader.data.service

import com.sugarspoon.qrreader.data.dao.VirtualCardDao
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VirtualCardRepository(private val virtualCardDao: VirtualCardDao) {

    val allCards: Flow<List<VirtualCardEntity>> = virtualCardDao.getAll()

    fun getVirtualCardById(id: Int): VirtualCardEntity {
        return virtualCardDao.getVirtualCardById(id)
    }

    fun insert(card: VirtualCardEntity) {
        CoroutineScope(IO).launch {
            virtualCardDao.insertAll(card)
        }
    }

    suspend fun delete(card: VirtualCardEntity) {
        CoroutineScope(IO).launch {
            virtualCardDao.delete(card)
        }
    }
}