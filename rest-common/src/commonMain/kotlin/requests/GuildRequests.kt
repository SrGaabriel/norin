package com.norin.rest.common.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateRequest(
    val name: String,
    val tag: String,
    @SerialName("owner_id") val ownerId: String
)

@Serializable
data class GuildUpdateRequest(
    val name: String,
    val tag: String,
    val ownerId: String,
)
