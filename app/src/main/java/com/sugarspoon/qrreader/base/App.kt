package com.sugarspoon.qrreader.base

import android.app.Application
import android.util.Log
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {

    lateinit var repository: VirtualCardRepository

    override fun onCreate() {
        super.onCreate()
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = QrDataBase.getDatabase(this, applicationScope)
        repository = VirtualCardRepository(database.virtualCardDao())
        Log.d("APP_", "passou no app")
    }

}