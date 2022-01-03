package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import com.norin.rest.common.entity.GuildMemberDTO
import com.norin.rest.common.requests.MemberJoinGuildRequest
import com.norin.rest.createStandardNullableRequest
import com.norin.rest.createStandardRequest
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

class MemberRoute(val wrapper: NorinRestWrapper) {
    fun integrateMember(request: MemberJoinGuildRequest): CompletableFuture<GuildMemberDTO> =
        wrapper.createStandardRequest("/guilds/${request.guildId}/members", HttpMethod.Post, request)

    fun retrieveMembership(userId: String, guildId: Int): CompletableFuture<GuildMemberDTO> =
        wrapper.createStandardNullableRequest("/guilds/$guildId/members/$userId", HttpMethod.Get, null)

    fun retrieveAllMemberships(guildId: Int): CompletableFuture<List<GuildMemberDTO>> =
        wrapper.createStandardRequest("/guilds/$guildId/members", HttpMethod.Get, null)

    fun disintegrateMember(userId: String, guildId: Int): CompletableFuture<GuildMemberDTO> =
        wrapper.createStandardRequest("/guilds/$guildId/members/$userId", HttpMethod.Delete, null)
}
