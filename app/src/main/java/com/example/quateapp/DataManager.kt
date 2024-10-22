package com.example.quateapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quateapp.Model.Quote
import com.google.gson.Gson

object DataManager {
    var data = emptyArray<Quote>()
    var curPage = mutableStateOf(Pages.LISTING)
    var curQuote: Quote? = null
    var isDataLoaded = mutableStateOf(false)
    fun loadAssetFromFile(context: Context){
      var inputStream = context.assets.open("quotes.json")
        var size = inputStream.available()
        var buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        var json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }
    fun switchPages(quote: Quote?) {
        if (curPage.value == Pages.LISTING) {
            curQuote = quote
            curPage.value = Pages.DETAIL
        } else {
            curPage.value = Pages.LISTING
        }
    }
}