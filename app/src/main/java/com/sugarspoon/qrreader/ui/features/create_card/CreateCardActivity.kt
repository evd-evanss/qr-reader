package com.sugarspoon.qrreader.ui.features.create_card

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class CreateCardActivity : BaseActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        val navController = Navigation.findNavController(this, R.id.register_pix_host)
    }

    override fun onBackPressed() {
        if(navController.popBackStack().not())
            finish()
    }
}