package ru.grakhell.currencies.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.grakhell.currencies.ui.viewmodel.CurrenciesViewModel
import ru.grakhell.currencies.ui.viewmodel.ExRatesViewModel
import ru.grakhell.currencies.ui.vo.CurrencyObject

val UIModule = module {

    viewModel { CurrenciesViewModel(get()) }
    viewModel { params -> ExRatesViewModel(get(), currency = params.get(), date = params.get()) }

}