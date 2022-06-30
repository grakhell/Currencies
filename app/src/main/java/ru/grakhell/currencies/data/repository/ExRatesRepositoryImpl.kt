package ru.grakhell.currencies.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.domain.dai.ExRatesRepository
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import java.util.*

class ExRatesRepositoryImpl(private val dataSource: CurrencyDataSource): ExRatesRepository {
    override suspend fun getExRates(currency: Currency, date: Date?): DataFlow = withContext(Dispatchers.IO) {
        try {
            val result = dataSource.getExRatesForCurrency(currency.index, date)
            DataFlow.ExRatesSuccess(result.transformToModel())
        } catch (ex:Exception) {
            DataFlow.Failure(ex)
        }
    }
}