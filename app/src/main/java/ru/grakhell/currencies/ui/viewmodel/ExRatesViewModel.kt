package ru.grakhell.currencies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.grakhell.currencies.domain.cases.GetExRatesCase
import ru.grakhell.currencies.domain.model.DataFlow
import ru.grakhell.currencies.ui.vo.CurrencyObject
import ru.grakhell.currencies.ui.vo.DailyRateObject
import ru.grakhell.currencies.ui.vo.UiFlow
import java.lang.IllegalStateException
import java.util.*

class ExRatesViewModel(
    private val exRatesCase: GetExRatesCase,
    private val currency: CurrencyObject,
    private val date: Date?
): ViewModel() {

    private val _state = MutableStateFlow<UiFlow>(UiFlow.InProgress)
    val state:StateFlow<UiFlow> = _state

    fun fetch() {
        viewModelScope.launch {
            val result = exRatesCase.getExRatesForDay(currency.transformToModel(), date)
            when(result) {
                is DataFlow.ExRatesSuccess -> {
                    val rate = DailyRateObject.transformFromModel(result.rates)
                    _state.value = UiFlow.GotExRates(rate)
                }
                is DataFlow.Failure ->  {
                    _state.value = UiFlow.Error(result.cause)
                }
                else ->  {
                    _state.value = UiFlow.Error(IllegalStateException())
                }
            }
        }
    }
}