package com.norin.rest.common.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildDTO(
    val id: Int,
    val name: String,
    val tag: String,
    @SerialName("owner_id") val ownerId: String,
    val members: List<GuildMemberDTO>
)

@Serializable
data class GuildMemberDTO(
    @SerialName("unique_id") val uniqueId: String,
    @SerialName("guild_id") val guildId: Int,
    val position: Int,
)
