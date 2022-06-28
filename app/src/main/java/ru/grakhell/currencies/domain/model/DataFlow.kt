package ru.grakhell.currencies.domain.model

sealed class DataFlow

open class Success:DataFlow()
class ExRatesSuccess(val rates:DailyRate):Success()
class CurrenciesSuccess(val currencies:List<Currency>):Success()

class Exception(val cause:Throwable, val retry: () -> Unit):DataFlow()
class Failure(val cause:Throwable):DataFlow()
