package com.sugarspoon.qrreader.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.extensions.setVisible
import kotlinx.android.synthetic.main.validate_view.view.*

class LoadingView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.validate_view, this, true)
    }

    fun setView(intent: LoadingIntent = LoadingIntent.CreateVirtualCard()) {
        intent.run {
            when(this) {
                is LoadingIntent.CreateVirtualCard -> setView(this.title, this.body)
                is LoadingIntent.VirtualCardList -> setView(this.title, this.body)
                is LoadingIntent.BarcodeList -> setView(this.title, this.body)
            }
        }
    }

    private fun setView(title: Int, body: Int) {
        context.run {
            validatePixTitleTv.run {
                text = getString(title)
            }
            validatePixBodyTv.run {
                text = getString(body)
            }
        }
    }

    fun displayLoading(isLoading: Boolean) {
        validatePixViewCl.run {
            setVisible(isLoading)
        }
    }

    sealed class LoadingIntent {
        class CreateVirtualCard(
            val title: Int = R.string.validate_generic_loading_title,
            val body: Int = R.string.validate_virtual_card_loading_body
        ): LoadingIntent()
        class VirtualCardList(
            val title: Int = R.string.validate_generic_loading_title,
            val body: Int = R.string.my_cards_loading_body
        ): LoadingIntent()
        class BarcodeList(
            val title: Int = R.string.validate_generic_loading_title,
            val body: Int = R.string.barcode_list_loading_body
        ): LoadingIntent()
    }
}