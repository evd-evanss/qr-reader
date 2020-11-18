package com.sugarspoon.qrreader.ui.features.create_card.network

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
import kotlinx.android.synthetic.main.fragment_network.view.*

class RegisterNetworkFragment:
    BaseFragment(),
    RegisterNetworkContract.View{

    private val presenter: RegisterNetworkContract.Presenter by lazy {
        val arguments: RegisterNetworkFragmentArgs by navArgs()
        val presenter = RegisterNetworkPresenter(arguments)
        presenter
    }

    override val site: String
        get() = view?.run { createCardSiteEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_network, container, false)
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
            setToolbar(ToolbarOptions.RegisterCardNetwork(), false)
        }
    }

    override fun enableContinue(isVisible: Boolean) {
        view?.run {
            createCardSiteBt.isEnabled = isVisible
        }
    }

    override fun setListeners() {
        view?.run {
            createCardSiteBt.setOnClickListener {
                presenter.onContinueClicked()
            }

            createCardSiteEt.afterTextChanged {
                presenter.afterTextChanged(text = it)
            }
        }
    }

    override fun openNextStep(card: VirtualCardEntity) {
        findNavController().navigate(
            RegisterNetworkFragmentDirections
                .actionRegisterNetworkFragmentToRegisterColorFragment(card)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}