package com.sugarspoon.qrreader.ui.features.barcode.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import com.sugarspoon.qrreader.utils.extensions.setQrCodeByString
import kotlinx.android.synthetic.main.item_barcodes.view.*

class BarcodeListAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var onDeleteItem: ((BarcodeEntity) -> Unit)? = null
    var onOpenBrowser: ((BarcodeEntity) -> Unit)? = null

    companion object {
        private const val EMPTY = 0
        private const val FILLED = 1
    }

    var list: MutableList<BarcodeEntity> = mutableListOf()

    fun setCardList(cardList: List<BarcodeEntity>) {
        list.addAll(cardList)
        notifyDataSetChanged()
    }

    fun delete() {
        val lastPosition = list.size - 1
        if (lastPosition >= 0) {
            list.removeAt(lastPosition)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is BarcodeViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return BarcodeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_barcodes, parent, false)
        )
    }

    override fun getItemCount() = list.size

    inner class BarcodeViewHolder(itemView: View) : BaseViewHolder<BarcodeEntity>(itemView) {
        override fun bind(item: BarcodeEntity) {
            itemView.apply {
                item.run {
                    itemBarcodeIv.setQrCodeByString(item.url)
                    itemBarcodeUrlTv.text = item.url
                    itemBarcodeDateTv.text = item.date
                }
                itemBarcodeDeleteIv.setOnClickListener {
                    onDeleteItem?.invoke(item)
                }
                itemBarcodeOpenBrowserIv.setOnClickListener {
                    onOpenBrowser?.invoke(item)
                }
            }
        }
    }

    interface Listener {
        fun onItemClicked(barcodeItem: BarcodeEntity)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
