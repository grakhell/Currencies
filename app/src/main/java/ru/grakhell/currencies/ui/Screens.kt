package ru.grakhell.currencies.ui

import java.lang.StringBuilder

sealed class Screens(val route:String) {
    object CurrenciesScreen:Screens("currencies")
    object RatesScreen:Screens("rates/{currency}?date={date}") {
        fun create(curr:String, date:String?):String {
            val builder = StringBuilder()
            builder.append("rates/")
                .append(curr)
            if (!date.isNullOrBlank()){
                builder.append("?date=${date}")
            }
            return builder.toString()
        }
    }
}
