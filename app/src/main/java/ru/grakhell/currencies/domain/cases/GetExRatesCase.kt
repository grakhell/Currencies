package ru.grakhell.currencies.domain.cases

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import java.util.*

interface GetExRatesCase {
    /**
     * Запрашивает обменные курсы выбранной валюты на указанный день
     * @param currency - валюта для которой требуются обменные курсы
     * @param date - дата на которую нужно получить обменные курсы, может быть null - тогда запрашиваются
     * актуальные курсы на текущее число
     * @return В случае успеха возвращает DataFlow.ExRatesSuccess, иначе DataFlow.Exception или DataFlow.Failure
     */
    suspend fun getExRatesForDay(currency: Currency, date: Date?):DataFlow
}