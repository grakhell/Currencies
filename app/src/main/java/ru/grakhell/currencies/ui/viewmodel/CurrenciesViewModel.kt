package ru.grakhell.currencies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.grakhell.currencies.domain.cases.GetCurrenciesCase
import ru.grakhell.currencies.domain.model.DataFlow
import ru.grakhell.currencies.ui.vo.CurrencyObject
import ru.grakhell.currencies.ui.vo.UiFlow
import java.lang.IllegalStateException

class CurrenciesViewModel(private val currenciesCase: GetCurrenciesCase): ViewModel() {

    private val _state = MutableStateFlow<UiFlow>(UiFlow.InProgress())
    val state:StateFlow<UiFlow> = _state

    init {
        viewModelScope.launch {
            val result = currenciesCase.getCurrenciesList()
            when(result) {
                is DataFlow.CurrenciesSuccess -> {
                    val list = result.currencies.map { cur -> CurrencyObject.transformFromModel(cur) }
                    _state.value = UiFlow.GotCurrencies(list)
                }
                is DataFlow.Failure -> {
                    _state.value = UiFlow.Error(result.cause)
                }
                else -> {
                    _state.value = UiFlow.Error(IllegalStateException())
                }
            }
        }
    }

    fun onItemClicked(item: CurrencyObject) {
        _state.value =  _state.value.addEffect(UiFlow.SideEffect.OnCurrencyClicked(item))
    }

    fun onClickDispatched() {
        _state.value =  _state.value.clearEffects()
    }
}