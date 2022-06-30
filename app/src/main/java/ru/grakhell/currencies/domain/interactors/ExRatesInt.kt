package ru.grakhell.currencies.domain.interactors

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.cases.GetExRatesCase
import ru.grakhell.currencies.domain.dai.ExRatesRepository
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import java.util.*

class ExRatesInt(private val repository: ExRatesRepository):GetExRatesCase {
    override suspend fun getExRatesForDay(currency: Currency, date: Date?): DataFlow {
        return repository.getExRates(currency, date)
    }
}