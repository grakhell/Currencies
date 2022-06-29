package ru.grakhell.currencies.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.domain.dai.CurrenciesRepository
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow

class CurrenciesRepositoryImpl(private val dataSource: CurrencyDataSource):CurrenciesRepository {

    override suspend fun getCurrencies(): DataFlow = withContext(Dispatchers.IO) {
        try {
            val result = dataSource.getCurrencies().map {
                Currency(it.key, it.value)
            }
            DataFlow.CurrenciesSuccess(result)
        } catch(ex:Exception) {
            DataFlow.Failure(ex)
        }
    }
}