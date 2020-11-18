package com.sugarspoon.qrreader.ui.features.create_card.name

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.extensions.afterTextChanged
import com.sugarspoon.qrreader.ui.features.card.VirtualCardContract
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.fragment_name.*

class RegisterNameFragment:
    BaseFragment(),
    RegisterNameContract.View {

    private val presenter: RegisterNameContract.Presenter by lazy {
        RegisterNamePresenter()
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
        presenter.attachedView(this)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setViews() {
        requireActivity().run {
            setToolbar(ToolbarOptions.RegisterCardName(), true)
        }
        view?.run {
            createCardNameBt.isEnabled = false
        }
    }

    override fun setListeners() {
        view?.run {
            createCardNameBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            createCardNameEt.afterTextChanged {
                presenter.afterTextChanged(text = it)
            }
        }
    }

    override fun enableContinue(isEnable: Boolean) {
        createCardNameBt.isEnabled = isEnable
    }

    override fun nextStep(virtualCardEntity: VirtualCardEntity) {
        view?.run {
            findNavController().navigate(
                RegisterNameFragmentDirections
                    .actionRegisterNameFragmentToRegisterEmailFragment(virtualCardEntity)
            )
        }
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
}