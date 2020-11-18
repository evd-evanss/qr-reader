package com.sugarspoon.qrreader.ui.features.create_card.phone

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
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.fragment_telephone.*

class RegisterPhoneFragment :
    BaseFragment(),
    RegisterPhoneContract.View{

    private val presenter: RegisterPhoneContract.Presenter by lazy {
        val arguments: RegisterPhoneFragmentArgs by navArgs()
        val presenter = RegisterPhonePresenter(arguments)
        presenter
    }

    override val phone: String?
        get() = view?.run { createCardPhoneEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_telephone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachedView(view = this)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setViews()  = view?.run {
        requireActivity().run {
            setToolbar(ToolbarOptions.RegisterCardTelephone(), false)
        }
    }

    override fun enableContinue(isVisible: Boolean) {
        view?.run {
            createCardTelephoneBt.isEnabled = isVisible
        }
    }

    override fun setListeners() {
        view?.run {
            createCardTelephoneBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            createCardPhoneEt.afterTextChanged {
                presenter.afterTextChanged(
                    text = it
                )
            }
        }
    }

    override fun openNextStep(cardEntity: VirtualCardEntity) {
        findNavController().navigate(
            RegisterPhoneFragmentDirections
                .actionRegisterPhoneFragmentToRegisterCompanyFragment(cardEntity)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}