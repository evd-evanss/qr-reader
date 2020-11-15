package com.sugarspoon.qrreader.utils

import com.sugarspoon.qrreader.R

sealed class ToolbarOptions {
    class Home(
        val icon: Int = R.drawable.ic_target,
        val title: Int = R.string.qr_code_view_title): ToolbarOptions()
    class QrList(
        val icon: Int = R.drawable.ic_qr_list,
        val title: Int = R.string.my_codes): ToolbarOptions()
    class CreateCard(
        val icon: Int = R.drawable.ic_edit,
        val title: Int = R.string.create_card): ToolbarOptions()
    class ListCards(
        val icon: Int = R.drawable.ic_virtual_card,
        val title: Int = R.string.my_cards): ToolbarOptions()
    class Help(
        val icon: Int = R.drawable.ic_help,
        val title: Int = R.string.help): ToolbarOptions()
}