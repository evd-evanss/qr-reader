package com.sugarspoon.qrreader.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class GenericRecyclerView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private lateinit var emptyView: View

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
    }

    private fun checkIfEmpty() {
        emptyView.let { emptyView ->
            adapter?.let { adapter ->
                emptyView.isVisible = adapter.itemCount == 0
            }
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        this.adapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        this.adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            checkIfEmpty()
        }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            checkIfEmpty()
        }
    }
}