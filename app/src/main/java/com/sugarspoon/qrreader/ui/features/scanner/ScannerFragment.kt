package com.sugarspoon.qrreader.ui.features.scanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.ResultQr
import com.sugarspoon.qrreader.ui.features.barcode_preview.BarcodePreviewActivity
import com.sugarspoon.qrreader.utils.PermissionsHelper
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.fragment_scanner.*

class ScannerFragment :
    BaseFragment(),
    PermissionsHelper.OnPermissionResult,
    ScannerContract.View {

    private val presenter: ScannerContract.Presenter by lazy {
        val presenter = ScannerPresenter(this)
        presenter
    }

    private val permissionsHelper by lazy {
        PermissionsHelper(
            activity = requireActivity(),
            requestCode = REQUEST_CODE_PERMISSIONS,
            permissions = REQUIRED_PERMISSIONS,
            onPermissionResult = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAtached(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
        val formats = listOf(BarcodeFormat.QR_CODE)
        scannerBarcodeCameraX.decoderFactory = DefaultDecoderFactory(formats)
        scannerBarcodeCameraX.resume()
    }

    override fun setViews() {
        requireActivity().run {
            setToolbar(ToolbarOptions.Home(), false)
        }
    }

    override fun dispatcherPermissions() {
        permissionsHelper.dispatchPermissions()
    }

    override fun displayError() {

    }

    override fun displayResult(result: BarcodeResult) {
        ResultQr.setResult(result)
        requireActivity().run {
            startActivity(Intent(this, BarcodePreviewActivity::class.java))
        }

    }

    override fun finalize() {
        requireActivity().finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            IntentIntegrator.parseActivityResult(
                requestCode,
                resultCode,
                data
            )
        }
    }

    override fun setListeners() {
        scannerLoadingPb.startAnimation()
        scannerBarcodeCameraX.decodeSingle(
            object : BarcodeCallback {
                override fun barcodeResult(result: BarcodeResult?) {
                    presenter.onBarcodeResult(result)
                }

                override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
                }
            }
        )
    }

    override fun onAllPermissionsGranted(requestCode: Int) {
        presenter.onPermissionsGranted()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsHelper.onRequestPermissionsResult(requestCode,
            permissions,
            grantResults)
    }

    override fun onPermissionsDenied(
        requestCode: Int,
        deniedPermissions: List<String>,
        deniedPermissionsWithNeverAskAgainOption: List<String>
    ) {
        presenter.onPermissionsDenied()
    }

    override fun onPause() {
        super.onPause()
        scannerBarcodeCameraX.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}