package com.sugarspoon.qrreader.widgets

import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.utils.Constants
import com.sugarspoon.qrreader.utils.PermissionsHelper
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import androidx.core.content.FileProvider
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ShareReceiptHelper : PermissionsHelper.OnPermissionResult {

    private var mActivity: Activity? = null
    private var mFragment: androidx.fragment.app.Fragment? = null
    private val context: Context
    private val permissionHelper: PermissionsHelper
    private val receiptContainer: ViewGroup
    private var viewsToHide: List<View>? = null


    constructor(activity: Activity, receiptContainer: ViewGroup, viewsToHide: List<View>? = null) {
        mActivity = activity
        this.receiptContainer = receiptContainer
        context = activity
        this.viewsToHide = viewsToHide
        permissionHelper = PermissionsHelper(activity, 300, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), this)
    }

    constructor(fragment: androidx.fragment.app.Fragment, receiptContainer: ViewGroup, viewsToHide: List<View>? = null) {
        mFragment = fragment
        mActivity = fragment.activity
        context = mActivity!!
        this.viewsToHide = viewsToHide
        this.receiptContainer = receiptContainer
        permissionHelper = PermissionsHelper(fragment, 300, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), this)
    }


    fun shareReceipt() {
        permissionHelper.dispatchPermissions()
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
    }

    private fun createReceiptAndShare() {
        setViewsVisibility(false)

        receiptContainer.post {

            val verticalPaddingSum = receiptContainer.paddingTop + receiptContainer.paddingBottom
            val bitmap = getBitmapFromView(receiptContainer, receiptContainer.getChildAt(0).measuredHeight + verticalPaddingSum, receiptContainer.measuredWidth)

            try {
                val file = createImageFile()
                val ostream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream)
                ostream.close()
                val share = Intent(Intent.ACTION_SEND)
                share.type = "image/*"
                val photoURI = FileProvider.getUriForFile(context,
                    Constants.AUTHORITY,
                    file)
                share.putExtra(Intent.EXTRA_STREAM, photoURI)
                context.startActivity(Intent.createChooser(share, context.getString(R.string.action_share_via)))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            setViewsVisibility(true)
        }
    }

    private fun setViewsVisibility(visible: Boolean) {
        viewsToHide?.let {
            it.forEach { view ->
                receiptContainer.findViewById<View>(view.id)?.visibility = if (visible) View.VISIBLE else View.GONE
            }
        }
    }


    private fun getBitmapFromView(view: View, totalHeight: Int, totalWidth: Int): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null)
            bgDrawable!!.draw(canvas)
        else
            canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    fun onRequestPermissionsResult(permsRequestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionHelper.onRequestPermissionsResult(permsRequestCode, permissions, grantResults)
    }

    override fun onAllPermissionsGranted(requestCode: Int) {
        createReceiptAndShare()
    }

    override fun onPermissionsDenied(requestCode: Int, deniedPermissions: List<String>, deniedPermissionsWithNeverAskAgainOption: List<String>) {

    }
}