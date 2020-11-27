package com.sugarspoon.qrreader.ui.features.card.create_card.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.utils.ToolbarOptions
import com.sugarspoon.qrreader.utils.extensions.afterTextChanged
import kotlinx.android.synthetic.main.fragment_address.*

class RegisterAddressFragment :
    BaseFragment(),
    RegisterAddressContract.View{

    private val presenter: RegisterAddressContract.Presenter by lazy {
        val arguments: RegisterAddressFragmentArgs by navArgs()
        val presenter = RegisterAddressPresenter(arguments)
        presenter
    }

    override val address: String
        get() = view?.run { createCardAddressEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachedView(view = this)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
        requireActivity().run {
            setToolbar(ToolbarOptions.RegisterCardAddress(), false)
        }
    }

    override fun setViews()  = view?.run {
        requireActivity().run {
            setToolbar(ToolbarOptions.CreateCard(), false)
        }
    }

    override fun enableContinue(isVisible: Boolean) {
        createCardAddressBt.isEnabled = isVisible
    }

    override fun setListeners() {
        view?.run {
            createCardAddressBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            createCardAddressEt.afterTextChanged {
                presenter.afterTextChanged(text = it)
            }
        }
    }

    override fun openNextStep(card: VirtualCardEntity) {
        findNavController().navigate(
            RegisterAddressFragmentDirections
                .actionRegisterAddressFragmentToRegisterPhoneFragment(card)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}