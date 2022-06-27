package ru.grakhell.currencies.domain.interactors

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.cases.GetCurrenciesCase
import ru.grakhell.currencies.domain.dai.CurrenciesRepository
import ru.grakhell.currencies.domain.model.DataFlow

class CurrenciesInt(private val repository: CurrenciesRepository):GetCurrenciesCase {
    override fun getCurrenciesList(): Flow<DataFlow> {
        return repository.getCurrencies()
    }
}