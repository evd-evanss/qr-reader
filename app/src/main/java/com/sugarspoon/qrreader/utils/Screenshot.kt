package com.sugarspoon.qrreader.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import java.io.*

object Screenshot {

    fun takeScreenshot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        storeScreenshot(bitmap, "Meu Cartão Virtual")
        return bitmap
    }

    fun takeScreenshotFromView(view: View, defaultColor: Int): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(defaultColor)
        view.draw(canvas)
        return bitmap
    }

    fun storeScreenshot(bitmap: Bitmap, filename: String) {
        val path: String =
            Environment.getExternalStorageDirectory().toString().toString() + "/" + filename
        var out: OutputStream? = null
        val imageFile = File(path)
        try {
            out = FileOutputStream(imageFile)
            // choose JPEG format
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
        } catch (e: FileNotFoundException) {
            // manage exception ...
        } catch (e: IOException) {
            // manage exception ...
        } finally {
            try {
                out?.close()
            } catch (exc: Exception) {
            }
        }
    }
//
//    private fun saveImage(bitmap: Bitmap, context: Context, folderName: String) {
//        if (android.os.Build.VERSION.SDK_INT >= 29) {
//            val values = contentValues()
//            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folderName)
//            values.put(MediaStore.Images.Media.IS_PENDING, true)
//            // RELATIVE_PATH and IS_PENDING are introduced in API 29.
//
//            val uri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//            if (uri != null) {
//                saveImageToStream(bitmap, context.contentResolver.openOutputStream(uri))
//                values.put(MediaStore.Images.Media.IS_PENDING, false)
//                context.contentResolver.update(uri, values, null, null)
//            }
//        } else {
//            val directory = File(Environment.getExternalStorageDirectory().toString() + separator + folderName)
//            // getExternalStorageDirectory is deprecated in API 29
//
//            if (!directory.exists()) {
//                directory.mkdirs()
//            }
//            val fileName = System.currentTimeMillis().toString() + ".png"
//            val file = File(directory, fileName)
//            saveImageToStream(bitmap, OutputStream(file))
//            if (file.absolutePath != null) {
//                val values = contentValues()
//                values.put(MediaStore.Images.Media.DATA, file.absolutePath)
//                // .DATA is deprecated in API 29
//                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//            }
//        }
//    }
//
//    private fun contentValues() : ContentValues {
//        val values = ContentValues()
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
//        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
//        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//        return values
//    }
//
//    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
//        if (outputStream != null) {
//            try {
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//                outputStream.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}