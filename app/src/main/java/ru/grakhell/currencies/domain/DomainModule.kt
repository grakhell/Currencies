package ru.grakhell.currencies.domain

import org.koin.dsl.module
import ru.grakhell.currencies.domain.cases.GetCurrenciesCase
import ru.grakhell.currencies.domain.cases.GetExRatesCase
import ru.grakhell.currencies.domain.interactors.CurrenciesInt
import ru.grakhell.currencies.domain.interactors.ExRatesInt

val DomainModule = module{
    factory<GetCurrenciesCase> { CurrenciesInt(get())}
    factory<GetExRatesCase> { ExRatesInt(get())}
}