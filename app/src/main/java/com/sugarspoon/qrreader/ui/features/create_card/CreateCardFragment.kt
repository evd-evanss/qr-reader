package com.sugarspoon.qrreader.ui.features.create_card

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.ui.features.card.VirtualCardActivity
import com.sugarspoon.qrreader.utils.CreatePix
import com.sugarspoon.qrreader.utils.ToolbarOptions
import com.sugarspoon.qrreader.widgets.LoadingView
import kotlinx.android.synthetic.main.fragment_create.*
import org.jetbrains.anko.support.v4.toast

class CreateCardFragment :
    BaseFragment(),
    CreatePix.CreatePixListener,
    CreateCardContract.View{

    private val presenter: CreateCardContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(requireContext())
        val repository = VirtualCardRepository(database.virtualCardDao())
        val presenter = CreateCardPresenter(this, repository)
        presenter
    }

    private val generatePix by lazy {
        CreatePix(this)
    }

    override val name: String
        get() = view?.run { createCardNameEt.text.toString() } ?: ""
    override val email: String
        get() = view?.run { createCardEmailEt.text.toString() } ?: ""
    override val tel: String
        get() = view?.run { createCardPhoneEt.text.toString() } ?: ""
    override val address: String
        get() = view?.run { createCardAddressEt.text.toString() } ?: ""
    override val company: String
        get() = view?.run { createCardCompanyEt.text.toString() } ?: ""
    override val site: String
        get() = view?.run { createCardSiteEt.text.toString() } ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
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
            createCardGenerateLv.setView(LoadingView.LoadingIntent.CreateVirtualCard())
        }
    }

    override fun setListeners() {
        view?.run {
            createCardGenerateBt.setOnClickListener {
                presenter.onGenerateVirtualCardClicked()
                createCardGenerateLv.displayLoading(true)
            }
            chooseColorRedRb.setOnClickListener {
                chooseColor()
            }
            chooseColorBlueRb.setOnClickListener {
                chooseColor()
            }
            chooseColorGreenRb.setOnClickListener {
                chooseColor()
            }
        }
    }

    private fun chooseColor() {
        when(chooseColorRb.checkedRadioButtonId) {
            R.id.chooseColorRedRb -> presenter.chooseColorRed()
            R.id.chooseColorBlueRb -> presenter.chooseColorBlue()
            R.id.chooseColorGreenRb -> presenter.chooseColorGreen()
        }
    }

    override fun chooseCardColor(color: Int) {
        view?.run {
            chooseColorTitle.setTextColor(ContextCompat.getColor(requireContext(), color))
        }
    }

    override fun generateVirtualCard(virtualCard: VirtualCardEntity) {
        view?.run {
            toast("Inserido com sucesso")
            startActivity(Intent(requireContext(), VirtualCardActivity::class.java))
        }
    }

    override fun displayFieldError() {
        toast("Erro")
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