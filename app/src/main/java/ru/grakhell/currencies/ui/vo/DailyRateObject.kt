package ru.grakhell.currencies.ui.vo

import ru.grakhell.currencies.domain.model.DailyRate
import java.util.*

data class DailyRateObject(
    val rates:List<ExRateObject>
) {
    companion object {
        @JvmStatic
        fun transformFromModel(model: DailyRate):DailyRateObject {
            return DailyRateObject(model.rates.map { rate -> ExRateObject.transformFromModel(rate)})
        }
    }
}
