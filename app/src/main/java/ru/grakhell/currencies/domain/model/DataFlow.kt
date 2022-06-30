package ru.grakhell.currencies.domain.model

sealed class DataFlow {

    open class Success : DataFlow()
    class ExRatesSuccess(val rates: DailyRate) : Success()
    class CurrenciesSuccess(val currencies: List<Currency>) : Success()

    class Failure(val cause: Throwable) : DataFlow()
}

