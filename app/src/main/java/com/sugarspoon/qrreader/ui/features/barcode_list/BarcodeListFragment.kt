package com.sugarspoon.qrreader.ui.features.barcode_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import com.sugarspoon.qrreader.data.service.BarcodeRepository
import com.sugarspoon.qrreader.extensions.setVisible
import com.sugarspoon.qrreader.utils.ToolbarOptions
import com.sugarspoon.qrreader.widgets.GenericDialog
import com.sugarspoon.qrreader.widgets.LoadingView
import kotlinx.android.synthetic.main.fragment_barcode_list.*
import org.jetbrains.anko.support.v4.toast

class BarcodeListFragment :
    BaseFragment(),
    BarcodeListContract.View {

    private val presenter: BarcodeListContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(requireContext())
        val repository = BarcodeRepository(database.barcodeDao())
        val presenter = BarcodeListPresenter(this, repository)
        presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_barcode_list, container, false)
    }

    private val listAdapter: BarcodeListAdapter by lazy {
        val adapter = BarcodeListAdapter(
            object : BarcodeListAdapter.Listener {
                override fun onItemClicked(barcodeItem: BarcodeEntity) {
                    presenter.onItemClicked(barcodeItem)
                }
            }
        )
        adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachedView(this)
        presenter.onViewResumed()
    }

    override fun setListeners() {
        requireActivity().run {
            listAdapter.onDeleteItem = { barcodeEntity ->
                GenericDialog(
                    context = this,
                    title = R.string.delete_dialog_title,
                    body = R.string.delete_dialog_body,
                    listener = object : GenericDialog.GenericDialogListener {
                        override fun onConfirm() {
                            presenter.onDeleteItem(barcodeEntity)
                        }
                        override fun onCancel() {}
                    }
                ).show()
            }
            listAdapter.onOpenBrowser = { barcodeEntity ->
                presenter.onOpenBrowser(barcodeEntity)
            }
        }
    }

    override fun setViews() {
        view?.run {
            requireActivity().run {
                barcodeListLoadingLv.setView(LoadingView.LoadingIntent.BarcodeList())
                setToolbar(ToolbarOptions.QrList(), false)
            }
        }
    }

    override fun displayError(message: String?) {
        toast(message ?: "")
    }

    override fun displayBarcodeList(list: List<BarcodeEntity>) {
        view?.run {
            barcodeListLoadingLv.displayLoading(false)
            barcodeListRv.layoutManager = LinearLayoutManager(requireContext())
            barcodeListRv.setEmptyView(emptyBarcodeCl)
            barcodeListRv.adapter = listAdapter
            listAdapter.list.clear()
            listAdapter.setCardList(list)
        }
    }

    override fun displayLoading(isLoading: Boolean) {
        view?.run {
            barcodeListLoadingLv.displayLoading(isVisible)
        }
    }

    override fun openBrowser(barcodeItem: BarcodeEntity) {
        requireActivity().run {
            GenericDialog(
                context = this,
                title = R.string.generic_dialog_title,
                body = R.string.generic_dialog_body,
                listener = object : GenericDialog.GenericDialogListener {
                    override fun onConfirm() {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(barcodeItem.url)
                        startActivity(intent)
                    }
                    override fun onCancel() {}
                }
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onDetach() {
        super.onDetach()
    }
}