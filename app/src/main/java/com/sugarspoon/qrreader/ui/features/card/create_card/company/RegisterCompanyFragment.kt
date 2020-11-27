package com.sugarspoon.qrreader.ui.features.card.create_card.company

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
import kotlinx.android.synthetic.main.fragment_company.*

class RegisterCompanyFragment :
    BaseFragment(),
    RegisterCompanyContract.View{

    private val presenter: RegisterCompanyContract.Presenter by lazy {
        val arguments: RegisterCompanyFragmentArgs by navArgs()
        val presenter = RegisterCompanyPresenter(arguments)
        presenter
    }

    override val company: String
        get() = view?.run { createCardCompanyEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company, container, false)
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
            setToolbar(ToolbarOptions.RegisterCardCompany(), false)
        }
    }

    override fun setListeners() {
        view?.run {
            createCardCompanyBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            createCardCompanyEt.afterTextChanged {
                presenter.afterTextChanged(text = it)
            }
        }
    }

    override fun enableContinue(isVisible: Boolean) {
        view?.run {
            createCardCompanyBt.isEnabled = isVisible
        }
    }

    override fun openNextStep(card: VirtualCardEntity) {
        findNavController().navigate(
            RegisterCompanyFragmentDirections
                .actionRegisterCompanyFragmentToRegisterNetworkFragment(card)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}