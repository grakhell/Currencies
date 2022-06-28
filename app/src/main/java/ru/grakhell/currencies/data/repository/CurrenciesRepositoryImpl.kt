package ru.grakhell.currencies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.domain.dai.CurrenciesRepository
import ru.grakhell.currencies.domain.model.CurrenciesSuccess
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import ru.grakhell.currencies.domain.model.Failure

class CurrenciesRepositoryImpl(private val dataSource: CurrencyDataSource):CurrenciesRepository {

    override fun getCurrencies(): Flow<DataFlow> = flow {
        try {
            val result = dataSource.getCurrencies().map {
                Currency(it.key, it.value)
            }
            emit(CurrenciesSuccess(result))
        } catch(ex:Exception) {
            emit(Failure(ex))
        }
    }
}