package com.sugarspoon.qrreader.utils.extensions

import android.text.TextUtils
import android.util.Patterns

fun String.addMask(inputMask: String, substitute: Char? = '#'): String {
    var mask = inputMask
    var unmaskedString = this.unmask()
    var index = 0
    var result = ""
    if (mask == MaskedType.CPF_OR_CNPJ) {
        mask = if (unmaskedString.length > 11) MaskedType.CNPJ else MaskedType.CPF
    }
    if (mask == MaskedType.CNPJ) {
        unmaskedString.addZerosUntilLength(14)
    } else if (mask == MaskedType.CPF) {
        unmaskedString.addZerosUntilLength(11)
    }
    mask.forEach { item ->
        if (index < unmaskedString.length) {
            if (item == substitute) {
                result += unmaskedString[index]
                index++
            } else {
                result += item
            }
        }
    }
    return result
}

fun String.addZerosUntilLength(quantity: Int): String {
    var result = this
    if(this.length < quantity) {
        for(i in 1..(quantity-this.length)) {
            result = "0$result"
        }
    }
    return result
}

fun String.unmask() = MaskedText.unMask(this)

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()