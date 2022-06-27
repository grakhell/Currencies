package ru.grakhell.currencies.data.datasource

import io.ktor.client.*
import io.ktor.client.request.*

private const val BASE_URL = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"

// TODO: 27.06.2022 Заменить возвращаемый тип на правильный
internal object CurrencyAPI {
    suspend fun getCurrenciesList(client:HttpClient):Unit {
        client.get {
            url(BASE_URL + "latest/currencies.json")
        }
    }

    suspend fun getExRatesForCurrency(client:HttpClient, code:String, date:String):Unit {
        client.get {
            url { BASE_URL + "${date}/currencies/${code}.json" }
        }
    }
}