package com.sugarspoon.qrreader.data.model

import java.io.Serializable

data class PixTemplate(
    val id: String,
    val size: Int,
    val value: String
) : Serializable{

}

fun String.decodeStaticPixQrCode(): MutableList<PixTemplate> {
    val positionLength = 4
    var index2 = -1
    var index3 = -1
    var index4 = -1
    var index5 = -1
    var index6 = -1
    var index7 = -1
    var index8 = -1
    var index9 = -1
    var index10 = -1
    var content = ""
    val listPixTemplates = mutableListOf<PixTemplate>()

    this.forEach {
        content += it
        if(content.length == positionLength) {
            val pixTemplate = getPixTemplate(content, this)
            index2 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index2) {
            val pixTemplate = getPixTemplate(content, this)
            index3 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index3) {
            val pixTemplate = getPixTemplate(content, this)
            index4 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index4) {
            val pixTemplate = getPixTemplate(content, this)
            index5 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index5) {
            val pixTemplate = getPixTemplate(content, this)
            index6 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index6) {
            val pixTemplate = getPixTemplate(content, this)
            index7 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index7) {
            val pixTemplate = getPixTemplate(content, this)
            index8 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index8) {
            val pixTemplate = getPixTemplate(content, this)
            index9 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index9) {
            val pixTemplate = getPixTemplate(content, this)
            index10 = content.length + pixTemplate.value.length + positionLength
            listPixTemplates.add(pixTemplate)
        }
        if(content.length == index10) {
            val pixTemplate = getPixTemplate(content, this)
            listPixTemplates.add(pixTemplate)
        }
    }
    return listPixTemplates
}

fun getPixTemplate(content: String, payload: String) : PixTemplate {
    val id = content.take(2)
    val size = content.takeLast(2).toInt()
    return PixTemplate(
        id = id,
        size = size,
        value = payload.take(content.length + size).takeLast(size)
    )
}