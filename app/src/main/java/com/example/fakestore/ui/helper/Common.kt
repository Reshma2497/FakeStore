package com.example.fakestore.ui.helper

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun getCurrency(price: Double): String {

    val currency = Currency.getInstance("USD")
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        this.currency = currency
    }
    return currencyFormat.format(price)

}