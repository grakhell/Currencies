package ru.grakhell.currencies.data.datasource

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val BASE_URL = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"

internal object CurrencyAPI {
    suspend fun getCurrenciesList(client:HttpClient): HttpResponse {
        return client.get(BASE_URL) {
            url { appendPathSegments("latest", "currencies.json") }
        }
    }

    suspend fun getExRatesForCurrency(client:HttpClient, code:String, date:String): HttpResponse {
        return client.get(BASE_URL) {
            url { appendPathSegments(date,"currencies","${code}.json" )}
        }
    }
}