package ru.grakhell.currencies.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import ru.grakhell.currencies.data.datasource.CurrencyDataSource
import ru.grakhell.currencies.data.datasource.CurrencyNetDataSource
import ru.grakhell.currencies.data.repository.CurrenciesRepositoryImpl
import ru.grakhell.currencies.data.repository.ExRatesRepositoryImpl
import ru.grakhell.currencies.domain.dai.CurrenciesRepository
import ru.grakhell.currencies.domain.dai.ExRatesRepository

val DataModule = module {
    single {
        HttpClient(Android)  {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    factory<CurrencyDataSource> { CurrencyNetDataSource(get()) }

    factory<CurrenciesRepository> { CurrenciesRepositoryImpl(get()) }
    factory<ExRatesRepository> { ExRatesRepositoryImpl(get()) }
}