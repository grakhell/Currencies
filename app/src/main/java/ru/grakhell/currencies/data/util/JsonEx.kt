package ru.grakhell.currencies.data.util

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.grakhell.currencies.data.dto.ExRateEntity
import java.lang.IllegalArgumentException
import java.util.*


fun JsonObject.parseToExRate():ExRateEntity {
    val keys = this.keys
    if (keys.size != 2) throw IllegalArgumentException()
    val d = this[keys.first()]?.jsonPrimitive?.content?:throw IllegalArgumentException()
    val c = keys.last()
    val rates = this[c]?.jsonObject?:throw IllegalArgumentException()
    val map = rates.map { key -> Pair(key.key, key.value.jsonPrimitive.double)}
    return ExRateEntity(d, c, map.toMap())
}