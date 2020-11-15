package com.sugarspoon.qrreader.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BarcodeDao {
    @Query("SELECT * FROM qr_code_table")
    fun getAll(): Flow<List<BarcodeEntity>>

    @Query("SELECT * FROM qr_code_table WHERE id IN (:barcodeId)")
    fun loadAllByIds(barcodeId: IntArray): Flow<List<BarcodeEntity>>

    @Insert
    fun insertAll(barcode: BarcodeEntity)

    @Delete
    fun delete(barcode: BarcodeEntity)

    @Query("SELECT * FROM qr_code_table WHERE id = :id")
    fun getBarcodeById(id: Int): BarcodeEntity
}

