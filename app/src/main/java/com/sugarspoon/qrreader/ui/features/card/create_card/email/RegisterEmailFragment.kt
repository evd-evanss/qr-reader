package com.sugarspoon.qrreader.ui.features.card.create_card.email

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
import com.sugarspoon.qrreader.utils.extensions.isValidEmail
import kotlinx.android.synthetic.main.fragment_email.*

class RegisterEmailFragment :
    BaseFragment(),
    RegisterEmailContract.View{

    private val presenter: RegisterEmailContract.Presenter by lazy {
        val arguments: RegisterEmailFragmentArgs by navArgs()
        val presenter = RegisterEmailPresenter(arguments)
        presenter
    }

    override val email: String
        get() = view?.run { createCardEmailEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email, container, false)
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
            setToolbar(ToolbarOptions.RegisterCardEmail(), true)
        }
    }

    override fun enableContinue(isVisible: Boolean) {
        view?.run {
            createCardEmailBt.isEnabled = isVisible
        }
    }

    override fun setListeners() {
        view?.run {
            createCardEmailBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            createCardEmailEt.afterTextChanged {
                presenter.afterTextChanged(
                    it.isValidEmail()
                )
            }
        }
    }

    override fun openNextStep(virtualCardEntity: VirtualCardEntity) {
        findNavController().navigate(
            RegisterEmailFragmentDirections
                .actionRegisterEmailFragmentToRegisterAddressFragment(
                    virtualCardEntity
                )
        )
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