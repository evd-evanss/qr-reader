package com.sugarspoon.qrreader.ui.features.barcode_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.BarcodeEntity
import com.sugarspoon.qrreader.data.service.BarcodeRepository
import com.sugarspoon.qrreader.extensions.setVisible
import com.sugarspoon.qrreader.extensions.setup
import com.sugarspoon.qrreader.utils.ToolbarOptions
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

    override fun setListeners() {}

    override fun setViews() {
        view?.run {
            requireActivity().run {
                setToolbar(ToolbarOptions.QrList(), false)
            }
            barcodeListRv.setup(
                adapter = listAdapter as RecyclerView.Adapter<in RecyclerView.ViewHolder>,
                layoutManager = LinearLayoutManager(requireContext()),
                hasFixedSize = true
            )
        }
    }

    override fun displayError(message: String?) {
        toast(message ?: "")
    }

    override fun displayBarcodeList(list: List<BarcodeEntity>) {
        listAdapter.list.clear()
        listAdapter.setCardList(list)
    }

    override fun displayPlaceHolder() {
        listAdapter.setEmptyList(listOf(true))
    }

    override fun openBrowser(barcodeItem: BarcodeEntity) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onDetach() {
        super.onDetach()
    }
}