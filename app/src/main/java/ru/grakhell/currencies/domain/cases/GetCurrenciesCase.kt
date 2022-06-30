package ru.grakhell.currencies.domain.cases

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow

interface GetCurrenciesCase {
    /**
     * Запрашивает список доступных валют
     * @return В случае успеха возвращает DataFlow.CurrenciesSuccess, иначе DataFlow.Exception или DataFlow.Failure
     */
    suspend fun getCurrenciesList():DataFlow
}