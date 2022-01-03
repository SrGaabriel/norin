package com.norin.rest.common.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDTO(
    @SerialName("unique_id") val uniqueId: String,
    val cash: Int,
    @SerialName("guild_id") val guildId: Int?,
    @SerialName("logged_at") val loggedAt: Long,
    @SerialName("created_at") val createdAt: Long
)
