package com.sugarspoon.qrreader.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.sugarspoon.qrreader.R
import kotlinx.android.synthetic.main.scanning_view.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class ScanLoadingView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var job = Job()
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    // CoroutineScope


    private var finalUpY = 520.0f
    private var finalDownY = 0.0f
    init {
        LayoutInflater.from(context).inflate(R.layout.scanning_view, this, true)
        scanning.translationY = 5f
    }

    fun startAnimation() {
        if(job.isActive) job.cancel()
        job = Job()
        val coroutineScope = CoroutineScope(Main + job + handler)
        coroutineScope.launch {
            while (isActive) {
                moveToDown()
                moveToUp()
            }
        }
    }

    private suspend fun moveToDown() {
        var initialUpY = 0f
        while (initialUpY <= finalUpY) {
            initialUpY += 1f
            delay(1)
            scanning.y = initialUpY
            if(initialUpY == finalUpY) {
                break
            }
            Log.d("SCANN_VIE", "y = $initialUpY f: $finalUpY")
        }

    }

    private suspend fun moveToUp() {
        var initialDownY = 520f
        while (finalDownY < initialDownY) {
            initialDownY -= 1f
            delay(1)
            scanning.y = initialDownY
            if(initialDownY == 0f) {
                break
            }
            Log.d("SCANN_VIE", "y = $initialDownY f: $finalUpY")
        }
    }
}