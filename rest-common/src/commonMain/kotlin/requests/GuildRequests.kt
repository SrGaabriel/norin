package com.norin.rest.common.requests

import com.norin.rest.common.GuildMemberPosition
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
    @SerialName("owner_id") val ownerId: String,
    @SerialName("friendly_fire") val friendlyFire: Boolean
)

@Serializable
data class MemberJoinGuildRequest(
    @SerialName("user_id") val userId: String,
    @SerialName("guild_id") val guildId: Int
)

@Serializable
data class MemberUpdateRequest(
    val position: GuildMemberPosition
)
