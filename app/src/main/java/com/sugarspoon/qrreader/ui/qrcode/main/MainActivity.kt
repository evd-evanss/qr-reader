package com.sugarspoon.qrreader.ui.qrcode.main

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomAppBar.replaceMenu(R.menu.menu)
        val navController = Navigation.findNavController(this, R.id.register_pix_host)
        bottomAppBar.setupWithNavController(navController)
        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            navController.navigate(menuItem.itemId)
            true
        }

        openQrReaderFab?.setOnClickListener {
            findNavController(R.id.register_pix_host).navigate(R.id.scannerFragment)
        }
    }

    override fun onBackPressed() {
        if(navController.popBackStack().not())
            finish()
    }
}