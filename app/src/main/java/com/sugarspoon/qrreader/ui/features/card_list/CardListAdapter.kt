package com.sugarspoon.qrreader.ui.features.card_list

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.utils.extensions.MaskedType
import com.sugarspoon.qrreader.utils.extensions.addMask
import com.sugarspoon.qrreader.utils.extensions.setQrCodeByString
import kotlinx.android.synthetic.main.item_cards.view.*

class CardListAdapter(private val onCardClicked: OnColorListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<VirtualCardEntity> = mutableListOf()
    var onCardDelete: ((VirtualCardEntity) -> Unit)? = null

    fun setCardList(cardList: List<VirtualCardEntity>) {
        list.addAll(cardList)
        notifyDataSetChanged()
    }

    fun delete() {
        val lastPosition = list.size - 1
        if(lastPosition >= 0) {
            list.removeAt(lastPosition)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder) {
            holder.bind(list[position], onCardClicked)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cards, parent, false)
        )
    }

    override fun getItemCount() = list.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cardItem: VirtualCardEntity, onColorListener: OnColorListener) {
            itemView.apply {
                cardItem.run {
                    readerContentIv.setQrCodeByString(cardItem.site)
                    itemCardTitleTv.text = cardItem.company
                    virtualCardName.text = cardItem.name
                    itemCardTelTv.text = cardItem.tel.addMask(MaskedType.CEL_PHONE)
                    itemCardEmailTv.text = cardItem.email
                    itemCardAddressTv.text = cardItem.address
                    itemCardSiteTv.text = cardItem.site
                    itemCardExtendIv.setOnClickListener {
                        onColorListener.onCardClicked(cardItem)
                    }
                    container.background.setColorFilter(context.getColor(cardItem.color), PorterDuff.Mode.SRC_IN)
                    itemCardDeleteIv.setOnClickListener {
                        onCardDelete?.invoke(cardItem)
                    }
                }
            }
        }
    }

    interface OnColorListener {
        fun onCardClicked(cardItem: VirtualCardEntity)
    }

}

