package ru.grakhell.currencies.ui.vo

import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DailyRate
import java.util.*

data class DailyRateObject(
    val currency:String,
    val date: Date,
    val rates:List<ExRateObject>
) {

    fun transformToModel(): DailyRate {
        val c = Currency(currency, "")
        return DailyRate(c, date, rates.map { rate -> rate.transformToModel() })
    }

    companion object {
        @JvmStatic
        fun transformFromModel(model: DailyRate):DailyRateObject {
            return DailyRateObject(model.currency.index, model.date, model.rates.map { rate -> ExRateObject.transformFromModel(rate)})
        }
    }
}
