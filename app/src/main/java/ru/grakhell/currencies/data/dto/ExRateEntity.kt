package ru.grakhell.currencies.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import ru.grakhell.currencies.domain.model.Currency
import ru.grakhell.currencies.domain.model.DailyRate
import ru.grakhell.currencies.domain.model.ExRate
import java.lang.IllegalArgumentException
import java.text.DateFormat
import java.text.SimpleDateFormat

@Serializable
data class ExRateEntity(
    val date: String,
    val currency:String,
    val rates:Map<String, Double>
) {

    @SuppressLint("SimpleDateFormat")
    fun transformToModel(): DailyRate {

        val d = SimpleDateFormat("yyyy-MM-dd").parse(date)?: throw IllegalArgumentException()
        val curr = Currency(currency, "")
        val r = rates.map { entry -> ExRate(Currency(entry.key, ""), entry.value) }
        return DailyRate(curr, d, r)
    }
}


