package com.sugarspoon.qrreader.ui.features.card_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.extensions.setup
import com.sugarspoon.qrreader.ui.features.card.VirtualCardActivity
import com.sugarspoon.qrreader.utils.ToolbarOptions
import com.sugarspoon.qrreader.widgets.ShareReceiptHelper
import kotlinx.android.synthetic.main.fragment_my_cards.*
import org.jetbrains.anko.support.v4.toast

class CardListFragment :
    BaseFragment(),
    CardListContract.View {

    private val presenter: CardListContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(requireContext())
        val repository = VirtualCardRepository(database.virtualCardDao())
        val presenter = CardListPresenter(this, repository)
        presenter
    }

    private var shareReceiptHelper: ShareReceiptHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_cards, container, false)
    }

    private val listAdapter: CardListAdapter by lazy {
        val adapter = CardListAdapter(
            object : CardListAdapter.OnColorListener {
                override fun onCardClicked(cardItem: VirtualCardEntity) {
                    presenter.onCardClicked(cardItem)
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
            setToolbar(ToolbarOptions.ListCards(), false)
            myCardsRv.setup(
                adapter = listAdapter,
                layoutManager = LinearLayoutManager(requireContext()),
                hasFixedSize = true
            )
        }
    }

    override fun displayCards(cardList: MutableList<VirtualCardEntity>) {
        listAdapter.list.clear()
        listAdapter.setCardList(cardList)
    }

    override fun displayError(message: String?) {
        toast(message ?: "Você não tem Cartões cadastrados")
    }

    override fun openShareHelper(content: VirtualCardEntity) {
        requireActivity().run {
            val intent = Intent(this, VirtualCardActivity::class.java)
            intent.putExtra(KEY, content.id)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        private const val KEY = "id"
    }
}