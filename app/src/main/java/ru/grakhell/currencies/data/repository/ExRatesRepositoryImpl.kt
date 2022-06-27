package ru.grakhell.currencies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.domain.dai.ExRatesRepository
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import java.util.*

class ExRatesRepositoryImpl(private val dataSource: CurrencyDataSource): ExRatesRepository {
    override fun getExRates(currency: Currency, date: Date?): Flow<DataFlow> = flow {
        TODO("Not yet implemented")
    }
}