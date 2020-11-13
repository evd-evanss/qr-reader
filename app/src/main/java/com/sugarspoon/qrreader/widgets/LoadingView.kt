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

    fun setView(intent: LoadingIntent) {
        intent.run {
            when(this) {
                is LoadingIntent.CreatePixIntent -> setView(this.title, this.body)
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
        class CreatePixIntent(
            val title: Int = R.string.validate_generic_loading_title,
            val body: Int = R.string.validate_pix_loading_body
        ): LoadingIntent()
    }
}