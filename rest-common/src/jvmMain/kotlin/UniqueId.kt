package com.norin.rest.common.util.jvm

import com.norin.rest.common.entity.AccountDTO
import com.norin.rest.common.entity.GuildDTO
import com.norin.rest.common.entity.GuildMemberDTO
import com.norin.rest.common.requests.AccountCreateRequest
import com.norin.rest.common.requests.GuildCreateRequest
import com.norin.rest.common.requests.GuildUpdateRequest
import java.util.*

private val memo = mutableMapOf<String, UUID>()

fun getPrettyUUID(uniqueId: String) =
    memo[uniqueId] ?: UUID.fromString(uniqueId).also { memo[uniqueId] = it }

val AccountDTO.prettyUniqueId: UUID
    get() = getPrettyUUID(uniqueId)

val AccountCreateRequest.prettyUniqueId: UUID
    get() = getPrettyUUID(uniqueId)

val GuildMemberDTO.prettyUniqueId: UUID
    get() = getPrettyUUID(uniqueId)

val GuildDTO.prettyOwnerUniqueId: UUID
    get() = getPrettyUUID(ownerId)

val GuildCreateRequest.prettyOwnerUniqueId: UUID
    get() = getPrettyUUID(ownerId)

val GuildUpdateRequest.prettyOwnerUniqueId: UUID?
    get() = if (ownerId == null) null else getPrettyUUID(ownerId)
