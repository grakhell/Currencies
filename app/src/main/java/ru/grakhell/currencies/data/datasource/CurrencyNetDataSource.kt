package ru.grakhell.currencies.data.datasource

import android.annotation.SuppressLint
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import ru.grakhell.currencies.data.dto.ExRateEntity
import ru.grakhell.currencies.data.util.parseToExRate
import java.text.SimpleDateFormat
import java.util.*


class CurrencyNetDataSource(private val client: HttpClient):CurrencyDataSource {

    override suspend fun getCurrencies(): Map<String, String> {
        return CurrencyAPI.getCurrenciesList(client).body()
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun getExRatesForCurrency(code: String, date: Date?): ExRateEntity {
        val dt = if (date != null) {
            val format = SimpleDateFormat("yyyy-MM-dd")
            format.format(date)
        } else {
            "latest"
        }
        val t = CurrencyAPI.getExRatesForCurrency(client, code, dt).bodyAsText()
        val body: JsonObject = Json.parseToJsonElement(t).jsonObject
        return body.parseToExRate()
    }
}