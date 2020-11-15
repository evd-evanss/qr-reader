package com.sugarspoon.qrreader.utils.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

suspend fun <T> Flow<T>.onCollect(
    onSuccess: (suspend (t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null
) {
    try {
        collect { result ->
            onSuccess?.let {
                CoroutineScope(Main).launch {
                    it(result)
                }
            }
        }
    } catch (e: Exception) {
        onError?.let {
            CoroutineScope(Main).launch {
                it(e)
            }
        }
    }
}
