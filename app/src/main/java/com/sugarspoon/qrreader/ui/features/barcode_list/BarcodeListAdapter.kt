package com.sugarspoon.qrreader.ui.features.barcode_list

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

    companion object {
        private const val EMPTY = 0
        private const val FILLED = 1
    }

    var list: MutableList<BarcodeEntity> = mutableListOf()

    var empty: MutableList<Boolean> = mutableListOf()

    fun setCardList(cardList: List<BarcodeEntity>) {
        list.addAll(cardList)
        notifyDataSetChanged()
    }

    fun setEmptyList(emptyList: List<Boolean>) {
        empty.addAll(emptyList)
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
        if (holder is EmptyViewHolder) {
            holder.bind(empty[position])
        }
        if (holder is BarcodeViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        position
        return if (list.isEmpty()) {
            EMPTY
        } else {
            FILLED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (list.size) {
            EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_bacode, parent, false)
            )
            else -> BarcodeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_barcodes, parent, false)
            )
        }
    }

    override fun getItemCount() = list.size

    class BarcodeViewHolder(itemView: View) : BaseViewHolder<BarcodeEntity>(itemView) {
        override fun bind(item: BarcodeEntity) {
            itemView.apply {
                item.run {
                    itemBarcodeIv.setQrCodeByString(item.url)
                    itemBarcodeUrlTv.text = item.url
                    itemBarcodeDateTv.text = item.date
                }
            }
        }
    }

    class EmptyViewHolder(itemView: View) : BaseViewHolder<Boolean>(itemView) {
        override fun bind(item: Boolean) {}
    }

    interface Listener {
        fun onItemClicked(barcodeItem: BarcodeEntity)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
