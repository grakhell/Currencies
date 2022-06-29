package ru.grakhell.currencies.ui.vo

sealed class UiFlow {

    open class Success():UiFlow()
    class GotCurrencies(val list: List<CurrencyObject>):Success()
    class GotExRates(val rates: DailyRateObject):Success()


    object InProgress:UiFlow()
    class OnCurrencyClicked(val currency: CurrencyObject): UiFlow()
    class Error(val cause:Throwable):UiFlow()
}
