package ru.grakhell.currencies.data.datasource

import java.util.*

// TODO: 27.06.2022 Заменить возвращаемый тип на правильный
interface CurrencyDataSource {

    suspend fun getCurrencies():Unit
    suspend fun getExRatesForCurrency(code:String, date:Date?):Unit
}