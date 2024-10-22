package com.example.quateapp.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.quateapp.Model.Quote

@Composable
fun QuoteList(data: Array<Quote>, onClick: ( quote: Quote) -> Unit) {
    LazyColumn {
        items(data.size) {
            QuoteListItem(data[it], onClick)
        }

    }
}