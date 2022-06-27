package ru.grakhell.currencies.data.datasource

import android.annotation.SuppressLint
import io.ktor.client.*
import java.text.SimpleDateFormat
import java.util.*


class CurrencyNetDataSource(private val client: HttpClient):CurrencyDataSource {

    override suspend fun getCurrencies() {
        CurrencyAPI.getCurrenciesList(client)
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun getExRatesForCurrency(code: String, date: Date?) {
        val dt = if (date != null) {
            val format = SimpleDateFormat("yyyy-MM-dd")
            format.format(date)
        } else {
            "latest"
        }
        CurrencyAPI.getExRatesForCurrency(client, code, dt)
    }
}