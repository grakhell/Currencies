package ru.grakhell.currencies.domain.model

import java.util.*

data class DailyRate(
    val currency: Currency,
    val date: Date,
    val rates:List<ExRate>
)
