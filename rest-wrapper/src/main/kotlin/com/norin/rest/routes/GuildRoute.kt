package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import com.norin.rest.common.entity.GuildDTO
import com.norin.rest.common.requests.GuildCreateRequest
import com.norin.rest.common.requests.GuildUpdateRequest
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

class GuildRoute(wrapper: NorinRestWrapper): RestRoute(wrapper) {
    fun createGuild(request: GuildCreateRequest): CompletableFuture<GuildDTO> =
        createStandardRequest("/guilds", HttpMethod.Post, request)

    fun searchGuild(id: Int): CompletableFuture<GuildDTO?> =
        createStandardNullableRequest("/guilds/$id", HttpMethod.Get, null)

    fun searchGuildByTag(tag: String): CompletableFuture<GuildDTO?> =
        createStandardNullableRequest("/guilds?tag=$tag", HttpMethod.Get, null)

    fun updateGuild(id: Int, request: GuildUpdateRequest): CompletableFuture<Unit> =
        createStandardRequest("/guilds/$id", HttpMethod.Patch, request)

    fun deleteGuild(id: Int): CompletableFuture<Unit> =
        createStandardRequest("/guilds/$id", HttpMethod.Delete, null)
}
