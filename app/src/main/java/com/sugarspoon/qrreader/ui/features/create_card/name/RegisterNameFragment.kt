package com.sugarspoon.qrreader.ui.features.create_card.name

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.fragment_name.*

class RegisterNameFragment :
    BaseFragment(),
    CreatePix.CreatePixListener,
    RegisterNameContract.View{

    private val presenter: RegisterNameContract.Presenter by lazy {
        val presenter = RegisterNamePresenter(this)
        presenter
    }

    private val generatePix by lazy {
        CreatePix(this)
    }

    override val name: String
        get() = view?.run { createCardNameEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
        requireActivity().run {
            setToolbar(ToolbarOptions.CreateCard(), false)
        }
    }

    override fun setViews()  = view?.run {
        requireActivity().run {
            setToolbar(ToolbarOptions.CreateCard(), false)
        }
    }

    override fun setListeners() {
        view?.run {
            createCardNameBt.setOnClickListener {

            }
        }
    }


    override fun onPixCreate(payload: String) {
        generatePix.clearPayload()
    }

    override fun onPause() {
        super.onPause()
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