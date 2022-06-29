package ru.grakhell.currencies.ui.vo

import ru.grakhell.currencies.domain.model.Currency

data class CurrencyObject(
    val index:String,
    val name:String
) {

    fun transformToModel(): Currency {
        return Currency( index, name )
    }
    companion object {
        @JvmStatic
        fun transformFromModel(model:Currency):CurrencyObject {
            return CurrencyObject(model.index, model.name)
        }
    }
}