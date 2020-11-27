package com.sugarspoon.qrreader.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.sugarspoon.qrreader.R
import java.io.*

class Screenshot(
    val context: Context,
    val onSuccess: () -> Unit,
    val onFail: () -> Unit
) {

    fun takeAndShare(view: ViewGroup) {
        val bitmap = getBitmapFromView(
            view,
            view.measuredHeight,
            view.measuredWidth
        )
        val file = createImageFile()
        shareImage(file, bitmap)
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            FILE_NAME,
            JPG,
            directory
        )
    }

    fun getBitmapFromView(view: View, totalHeight: Int, totalWidth: Int): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(
            totalWidth,
            totalHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null)
            bgDrawable.draw(canvas)
        else
            canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    private fun shareImage(file: File, bitmap: Bitmap) {
        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_QUALITY, fileOutputStream)
            fileOutputStream.close()
            val share = Intent(Intent.ACTION_SEND)
            share.type = FILE_TYPE
            val photoURI = FileProvider.getUriForFile(
                context,
                Constants.AUTHORITY,
                file
            )
            share.putExtra(Intent.EXTRA_STREAM, photoURI)
            context.startActivity(
                Intent.createChooser(
                    share,
                    context.getString(R.string.action_share_via)
                )
            )
            onSuccess()
        } catch (e: Exception) {
            print(e.message)
            onFail()
        }
    }

    companion object {
        private const val FILE_NAME = "Meu Cartão Virtual"
        private const val FILE_TYPE = "image/*"
        private const val BITMAP_QUALITY = 100
        private const val JPG = ".jpg"
    }
}