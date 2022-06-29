package ru.grakhell.currencies.domain.dai

import kotlinx.coroutines.flow.Flow
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DataFlow
import java.util.*

interface ExRatesRepository {
    suspend fun getExRates(currency: Currency, date: Date?): DataFlow
}