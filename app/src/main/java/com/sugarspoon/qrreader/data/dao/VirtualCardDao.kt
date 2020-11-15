package com.sugarspoon.qrreader.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VirtualCardDao {
    @Query("SELECT * FROM virtual_cards_table")
    fun getAll(): Flow<List<VirtualCardEntity>>

    @Query("SELECT * FROM virtual_cards_table WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<VirtualCardEntity>>

    @Insert
    fun insertAll(card: VirtualCardEntity)

    @Delete
    fun delete(card: VirtualCardEntity)

    @Query("SELECT * FROM virtual_cards_table WHERE id = :id")
    fun getVirtualCardById(id: Int): VirtualCardEntity
}

