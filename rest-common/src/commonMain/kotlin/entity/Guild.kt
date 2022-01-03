package com.norin.rest.common.entity

import com.norin.rest.common.GuildMemberPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GuildDTO(
    val id: Int,
    val name: String,
    val tag: String,
    @SerialName("owner_id") val ownerId: String
)

@Serializable
data class GuildMemberDTO(
    @SerialName("unique_id") val uniqueId: String,
    @SerialName("guild_id") val guildId: Int,
    val position: GuildMemberPosition,
)
