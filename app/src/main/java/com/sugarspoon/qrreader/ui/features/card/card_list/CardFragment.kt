package com.sugarspoon.qrreader.ui.features.card.card_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugarspoon.qrreader.R
import com.sugarspoon.qrreader.base.BaseFragment
import com.sugarspoon.qrreader.data.database.QrDataBase
import com.sugarspoon.qrreader.data.entity.VirtualCardEntity
import com.sugarspoon.qrreader.data.service.VirtualCardRepository
import com.sugarspoon.qrreader.ui.features.card.extend.VirtualCardActivity
import com.sugarspoon.qrreader.ui.features.card.create_card.CreateCardActivity
import com.sugarspoon.qrreader.utils.ToolbarOptions
import com.sugarspoon.qrreader.ui.widgets.GenericDialog
import com.sugarspoon.qrreader.utils.extensions.setVisible
import kotlinx.android.synthetic.main.fragment_my_cards.*

class CardFragment :
    BaseFragment(),
    CardContract.View {

    private val presenter: CardContract.Presenter by lazy {
        val database = QrDataBase.getDatabase(requireContext())
        val repository = VirtualCardRepository(database.virtualCardDao())
        val presenter = CardPresenter(this, repository)
        presenter
    }

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
                    presenter.onExtendClicked(cardItem)
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
        view?.run {
            listAdapter.onCardDelete = { item ->
                GenericDialog(
                    context = context,
                    title = R.string.delete_dialog_title,
                    body = R.string.delete_dialog_body,
                    listener = object : GenericDialog.GenericDialogListener {
                        override fun onConfirm() {
                            presenter.onDeleteItem(item)
                        }
                        override fun onCancel() {}
                    }
                ).show()
            }

            myCardsAddItemBt.setOnClickListener {
                presenter.onCardAddClicked()
            }
        }
    }

    override fun setViews() {
        view?.run {
            setToolbar(ToolbarOptions.ListCards(), false)
        }
    }

    override fun displayCards(cardList: MutableList<VirtualCardEntity>) {
        myCardsRv.layoutManager = LinearLayoutManager(requireContext())
        myCardsRv.setEmptyView(emptyCardListCl)
        myCardsRv.adapter = listAdapter
        listAdapter.list.clear()
        listAdapter.setCardList(cardList)
    }

    override fun displayEmpty() {
        view?.run {
            myCardsRv.layoutManager = LinearLayoutManager(requireContext())
            myCardsRv.setEmptyView(emptyCardListCl)
        }
    }

    override fun displayLoading(isLoading: Boolean) {
        view?.run {
        }
    }

    override fun enableRegister(enable: Boolean) {
        view?.run {
            myCardsAddItemBt.setVisible(enable)
        }
    }

    override fun setBackgroundForContainer(color: Int) {
        view?.run {
            myCardContainerLl.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), color)
        }
    }

    override fun openRegisterFlow() {
        view?.run {
            requireActivity().run {
                startActivity(Intent(this, CreateCardActivity::class.java))
            }
        }
    }

    override fun openViewExtended(content: VirtualCardEntity) {
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