package com.rogoz208.qafpay.data.remote.utils

import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rogoz208.qafpay.data.remote.dto.DataDto
import java.lang.reflect.Type

class DataDeserializer : JsonDeserializer<DataDto> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: com.google.gson.JsonDeserializationContext
    ): DataDto {
        val jsonObject = json.asJsonObject

        return when {
            jsonObject.has("server_time") && jsonObject.has("session_id") -> {
                DataDto.SessionOpenDataDto(
                    serverTime = jsonObject.get("server_time").asInt,
                    sessionId = jsonObject.get("session_id").asString
                )
            }

            jsonObject.has("user_id") -> {
                DataDto.SessionTestDataDto(
                    userId = jsonObject.get("user_id").asString
                )
            }

            jsonObject.has("otp") -> {
                DataDto.OtpSendDataDto(
                    code = jsonObject.get("code").asInt,
                    length = jsonObject.get("length").asInt,
                    otp = jsonObject.get("otp").asString,
                    timeLeft = jsonObject?.checkAndGet("time_left")?.asInt,
                    type = jsonObject.get("type").asString
                )
            }

            jsonObject.has("first_auth") -> {
                DataDto.OtpVerifyDataDto(
                    attempts = jsonObject.checkAndGet("attempts")?.asInt,
                    firstAuth = jsonObject.get("first_auth").asBoolean
                )
            }

            else -> throw IllegalArgumentException("Unknown data format")
        }
    }
}

private fun JsonObject?.checkAndGet(property: String): JsonElement? {
    return if (this?.has(property) == true) {
        val jsonElement = this.get(property)
        if (jsonElement.isJsonNull) {
            null
        } else {
            jsonElement
        }
    } else null
}