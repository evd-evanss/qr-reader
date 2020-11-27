package com.sugarspoon.qrreader.utils.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.io.Serializable

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}

fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.startActivityTransition(intent: Intent, idAnimationOut: Int,
                                     idAnimationIn: Int, delay: Long, requestCode: Int? = null) {
    if (requestCode == null) {
        Handler().postDelayed({
            this.startActivity(intent)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    } else {
        Handler().postDelayed({
            this.startActivityForResult(intent, requestCode)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    }
}

fun Activity.finishWithTransition(idAnimationOut: Int, idAnimationIn: Int, delay: Long) {
    Handler().postDelayed({
        this.finish()
        this.overridePendingTransition(idAnimationIn, idAnimationOut)
    }, delay)
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Activity.getSerializable(key: String): T {
    return intent.getSerializableExtra(key) as T
}

fun Context.copyToClipboard(content: String) {
    val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val myClip = ClipData.newPlainText("text", content)

    clipBoard?.setPrimaryClip(myClip)
}


//TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.openVideoPlayer(video: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(video), "video/*")
    startActivity(intent)
}

/**
 * Try open an app using his package name,
 * if the app is not installed on device, it redirects to play store.
 *
 * @param packageName Package name of the app to open
 */
fun Activity.openExternalApp(packageName: String) {
    try {
        startActivity(packageManager.getLaunchIntentForPackage(packageName))
    } catch (e: Exception) {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
        startActivity(intent)
    }
}

/**
 * Open BBC WhatsApp Chat.
 *
 * This method will open BBC whatsapp chat, if the app is installed
 * Otherwise will open the whatsapp web on the browser.
 *
 * @param whatsAppLink WhatsApp click-to-chat link
 * @link https://faq.whatsapp.com/en/android/26000030/?lang=pt_pt
 */
fun Activity.openWhatsAppChat(whatsAppLink: String) {
    try {
        startActivity(Intent().apply {
            data = Uri.parse(whatsAppLink)
            action = Intent.ACTION_VIEW
        })
    } catch (e: Exception) {

    }
}

fun Activity.openSettings(packageName: String? = null) {
    try {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName ?: getPackageName(), null)
        })
    } catch (e: Exception) {
        Log.e("LOGHELPER", e.localizedMessage)
    }
}

