package ru.grakhell.currencies.domain.dai

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.model.DataFlow

interface CurrenciesRepository {
    fun getCurrencies():Flow<DataFlow>
}