//package com.sugarspoon.pixreader.ui.qrcode.scanner
//
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import com.google.zxing.BarcodeFormat
//import com.google.zxing.ResultPoint
//import com.google.zxing.integration.android.IntentIntegrator
//import com.google.zxing.integration.android.IntentResult
//import com.journeyapps.barcodescanner.BarcodeCallback
//import com.journeyapps.barcodescanner.BarcodeResult
//import com.journeyapps.barcodescanner.DefaultDecoderFactory
//import com.sugarspoon.pixreader.R
//import com.sugarspoon.pixreader.base.BaseActivity
//import com.sugarspoon.pixreader.data.ResultQr
//import com.sugarspoon.pixreader.ui.qrcode.reader.ReaderActivity
//import com.sugarspoon.pixreader.utils.PermissionsHelper
//
//class ScannerActivity : BaseActivity(),
//    PermissionsHelper.OnPermissionResult,
//    ScannerContract.View {
//
//    private val presenter: ScannerContract.Presenter by lazy {
//        val presenter = ScannerPresenter(this)
//        presenter
//    }
//
//    private val permissionsHelper by lazy {
//        PermissionsHelper(
//            activity = this,
//            requestCode = REQUEST_CODE_PERMISSIONS,
//            permissions = REQUIRED_PERMISSIONS,
//            onPermissionResult = this
//        )
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_scanner)
//        presenter.onViewCreated()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        presenter.onViewResumed()
//        scannerBarcodeCameraX.resume()
//    }
//
//    override fun setViews() {
//        setToolbar(getString(R.string.qr_code_view_title), false)
//        val formats = listOf(BarcodeFormat.QR_CODE)
//        scannerBarcodeCameraX.decoderFactory = DefaultDecoderFactory(formats)
//    }
//
//    override fun dispatcherPermissions() {
//        permissionsHelper.dispatchPermissions()
//    }
//
//    override fun setBarcodeReader() {
//        scannerLoadingPb.startAnimation()
//        scannerBarcodeCameraX.decodeSingle(object : BarcodeCallback {
//            override fun barcodeResult(result: BarcodeResult?) {
//                presenter.onBarcodeResult(result)
//            }
//
//            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
//            }
//        })
//    }
//
//    override fun displayError() {
//
//    }
//
//    override fun displayResult(result: BarcodeResult) {
//        ResultQr.setResult(result)
//        startActivity(Intent(this, ReaderActivity::class.java))
//    }
//
//    override fun finalize() {
//        finish()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == Activity.RESULT_OK){
//            val result: IntentResult =
//                IntentIntegrator.parseActivityResult(
//                    requestCode,
//                    resultCode,
//                    data
//                )
//        }
//    }
//
//    override fun onAllPermissionsGranted(requestCode: Int) {
//        presenter.onPermissionsGranted()
//    }
//
//    override fun onPermissionsDenied(
//        requestCode: Int,
//        deniedPermissions: List<String>,
//        deniedPermissionsWithNeverAskAgainOption: List<String>
//    ) {
//        presenter.onPermissionsDenied()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        scannerBarcodeCameraX.pause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        scannerBarcodeCameraX.stopDecoding()
//    }
//
//    companion object {
//        private const val REQUEST_CODE_PERMISSIONS = 10
//        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
//    }
//}