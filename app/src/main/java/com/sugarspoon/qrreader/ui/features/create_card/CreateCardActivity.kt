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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
    }
}