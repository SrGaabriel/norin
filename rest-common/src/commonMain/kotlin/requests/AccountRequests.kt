package com.norin.rest.common.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountCreateRequest(
    @SerialName("unique_id") val uniqueId: String
)

@Serializable
data class AccountUpdateRequest(
    val cash: Int,
    @SerialName("guild_id") val guildId: Int,
    val loginDate: Long
)
