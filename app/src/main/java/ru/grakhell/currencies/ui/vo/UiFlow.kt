package ru.grakhell.currencies.ui.vo

sealed class UiFlow: Cloneable {

    protected var _effects = mutableListOf<SideEffect>()
    val effects: List<SideEffect>
    get() = _effects

    abstract fun addEffect(effect: SideEffect): UiFlow
    abstract fun clearEffects(): UiFlow

    protected fun add(other:UiFlow, effect: SideEffect) {
        this._effects = other._effects.toMutableList()
        this._effects.add(effect)
    }

    protected fun clear(other:UiFlow) {
        this._effects = other._effects.toMutableList()
        this._effects.clear()
    }

    abstract class Success():UiFlow()
    class GotCurrencies(val list: List<CurrencyObject>):Success() {

        override fun addEffect(effect: SideEffect): UiFlow {
            val copy = GotCurrencies(list)
            copy.add(this, effect)
            return copy
        }

        override fun clearEffects(): UiFlow {
            val copy = GotCurrencies(list)
            copy.clear(this)
            return copy
        }
    }
    class GotExRates(val rates: DailyRateObject):Success()
    {

        override fun addEffect(effect: SideEffect): UiFlow {
            val copy = GotExRates(rates)
            copy.add(this, effect)
            return copy
        }

        override fun clearEffects(): UiFlow {
            val copy = GotExRates(rates)
            copy.clear(this)
            return copy
        }
    }


    class InProgress:UiFlow()
    {

        override fun addEffect(effect: SideEffect): UiFlow {
            val copy = InProgress()
            copy.add(this, effect)
            return copy
        }

        override fun clearEffects(): UiFlow {
            val copy = InProgress()
            copy.clear(this)
            return copy
        }
    }

    class Error(val cause:Throwable):UiFlow()
    {
        override fun addEffect(effect: SideEffect): UiFlow {
            val copy = Error(cause)
            copy.add(this, effect)
            return copy
        }

        override fun clearEffects(): UiFlow {
            val copy = Error(cause)
            copy.clear(this)
            return copy
        }
    }

    open class SideEffect {
        class OnCurrencyClicked(val currency: CurrencyObject): SideEffect()
    }
}
