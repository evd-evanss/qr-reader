package com.sugarspoon.qrreader.ui.features.card.create_card.color

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.ui.features.card.extend.VirtualCardActivity
import com.sugarspoon.qrreader.utils.ToolbarOptions
import kotlinx.android.synthetic.main.fragment_choose_color.*

class RegisterColorFragment :
    BaseFragment(),
    RegisterColorContract.View{

    private val presenter: RegisterColorContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(requireContext())
        val repository = VirtualCardRepository(database.virtualCardDao())
        val arguments: RegisterColorFragmentArgs by navArgs()
        val presenter = RegisterColorPresenter(arguments, repository)
        presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_color, container, false)
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

    override fun setViews()  = view?.run {
        requireActivity().run {
            setToolbar(ToolbarOptions.RegisterCardColor(), false)
            createCardGenerateLv.setView()
        }
    }

    override fun setListeners() {
        view?.run {
            createCardColorBt.setOnClickListener {
                presenter.onContinueClicked()
            }
            chooseColorRedRb.setOnClickListener {
                presenter.chooseColor(color = R.color.red )
            }
            chooseColorBlueRb.setOnClickListener {
                presenter.chooseColor(color = R.color.color_blue_mid)
            }
            chooseColorGreenRb.setOnClickListener {
                presenter.chooseColor(color = R.color.green_mid)
            }
        }
    }

    override fun displayLoading(isLoading: Boolean) {
        view?.run {
            createCardGenerateLv.displayLoading(isLoading)
        }
    }

    override fun displayCardCreated(cardEntity: VirtualCardEntity) {
        view?.run {
            startActivity(Intent(requireContext(), VirtualCardActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}