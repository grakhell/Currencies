package ru.grakhell.currencies.data.datasource

import ru.grakhell.currencies.data.dto.ExRateEntity
import java.util.*

interface CurrencyDataSource {

    suspend fun getCurrencies():Map<String, String>
    suspend fun getExRatesForCurrency(code:String, date:Date?):ExRateEntity
}