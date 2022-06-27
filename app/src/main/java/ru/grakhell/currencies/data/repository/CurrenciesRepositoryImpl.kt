package ru.grakhell.currencies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.domain.dai.CurrenciesRepository
import ru.grakhell.currencies.domain.model.DataFlow

class CurrenciesRepositoryImpl(private val dataSource: CurrencyDataSource):CurrenciesRepository {

    override fun getCurrencies(): Flow<DataFlow> = flow {
        TODO("Not yet implemented")
    }
}