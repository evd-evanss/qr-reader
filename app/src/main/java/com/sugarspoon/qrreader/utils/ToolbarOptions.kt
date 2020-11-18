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
    class RegisterCardName(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_name): ToolbarOptions()
    class RegisterCardEmail(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_email): ToolbarOptions()
    class RegisterCardAddress(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_address): ToolbarOptions()
    class RegisterCardTelephone(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_telephone): ToolbarOptions()
    class RegisterCardCompany(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_company): ToolbarOptions()
    class RegisterCardNetwork(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_network): ToolbarOptions()
    class RegisterCardColor(
        val icon: Int? = null,
        val title: Int = R.string.register_card_title_color): ToolbarOptions()
}