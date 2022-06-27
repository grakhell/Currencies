package ru.grakhell.currencies.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyEntity(
    val code:String,
    val name:String
)
