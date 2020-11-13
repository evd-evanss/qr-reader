package com.sugarspoon.qrreader.extensions

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.google.android.material.snackbar.Snackbar

fun pxToDp(px: Int): Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Float): Int {
    val density = Resources.getSystem().displayMetrics.density
    return Math.round(dp * density)
}

fun Context.dpToFloat(dps: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps,
        this.resources.displayMetrics)
}

fun Context.spToFloat(dps: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dps,
        this.resources.displayMetrics)
}

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun androidx.recyclerview.widget.RecyclerView.setup(adapter: androidx.recyclerview.widget.RecyclerView.Adapter<in androidx.recyclerview.widget.RecyclerView.ViewHolder>,
                                                    layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager? = androidx.recyclerview.widget.LinearLayoutManager(this.context),
                                                    decoration: androidx.recyclerview.widget.RecyclerView.ItemDecoration? = null,
                                                    hasFixedSize: Boolean = true) {

    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    decoration?.let { this.addItemDecoration(it) }
}

//SNACK

fun snack(coordinator: androidx.coordinatorlayout.widget.CoordinatorLayout, message: String, retryText: String, action: (v: View) -> Unit?, indefinite: Boolean = true) {
    Snackbar.make(coordinator, message, if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG)
        .setAction(retryText, { v -> action(v) })
        .show()
}

fun snack(coordinator: androidx.coordinatorlayout.widget.CoordinatorLayout, message: String, indefinite: Boolean) =
    Snackbar.make(coordinator, message, if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG)
        .show()

fun snack(view: View, message: String, indefinite: Boolean) =
        Snackbar.make(view, message, if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG)
                .show()

//Animations
fun View.translate(animId: Int, duration: Long? = null) {
    val anim = AnimationUtils.loadAnimation(this.context, animId)
    if (duration != null) anim.duration = duration
    startAnimation(anim)
}

fun EditText.addTextWatcher(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(query.toString())
        }
    })
}

fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun View.oneClick(onClick: () -> Unit) {
    if (this.isEnabled) {
        setOnClickListener {
            this.isEnabled = false
            this.postDelayed({ this.isEnabled = true }, 3000)
            onClick()
        }
    }
}

fun View.onClickRegister(onClickRegister: () -> Unit) {
    if (this.isEnabled) {
        setOnClickListener {
            this.isEnabled = false
            this.postDelayed({ this.isEnabled = true }, 10000)
            onClickRegister()
        }
    }
}

fun ImageView.setColor(context: Context, @ColorRes colorRes: Int) {
    this.setColorFilter(ContextCompat.getColor(context, colorRes))
}

fun NestedScrollView.scrollToView(view: View) {
    Handler().post(Runnable {
        this.smoothScrollTo(0, view.bottom)
    })
}

fun ScrollView.scrollToView(view: View) {
    Handler().post(Runnable {
        this.smoothScrollTo(0, view.bottom)
    })
}