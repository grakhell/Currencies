package ru.grakhell.currencies.ui.vo

import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.ExRate

data class ExRateObject(
    val currencyIndex:String,
    val rate:Double
) {
    fun transformToModel():ExRate {
        val c = Currency(currencyIndex, "")
        return ExRate(c,  rate)
    }

    companion object  {
        @JvmStatic
        fun transformFromModel(model: ExRate):ExRateObject  {
            return ExRateObject(model.currency.index, model.rate)
        }
    }
}