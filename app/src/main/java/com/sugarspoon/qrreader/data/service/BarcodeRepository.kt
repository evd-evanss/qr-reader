package com.sugarspoon.qrreader.data.service

import com.sugarspoon.qrreader.data.dao.BarcodeDao
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BarcodeRepository(private val barcodeDao: BarcodeDao) {

    val allCards: Flow<List<BarcodeEntity>> = barcodeDao.getAll()

    fun getVirtualCardById(id: Int): BarcodeEntity {
        return barcodeDao.getBarcodeById(id)
    }

    fun insert(card: BarcodeEntity) {
        CoroutineScope(IO).launch {
            barcodeDao.insertAll(card)
        }
    }

    suspend fun delete(card: BarcodeEntity) {
        CoroutineScope(IO).launch {
            barcodeDao.delete(card)
        }
    }
}