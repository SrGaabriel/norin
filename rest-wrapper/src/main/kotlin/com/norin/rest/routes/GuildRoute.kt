package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import com.norin.rest.common.entity.GuildDTO
import com.norin.rest.common.requests.GuildCreateRequest
import com.norin.rest.common.requests.GuildUpdateRequest
import com.norin.rest.createStandardNullableRequest
import com.norin.rest.createStandardRequest
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

class GuildRoute(val wrapper: NorinRestWrapper) {
    fun createGuild(request: GuildCreateRequest): CompletableFuture<GuildDTO> =
        wrapper.createStandardRequest("/guilds", HttpMethod.Post, request)

    fun searchGuild(id: Int): CompletableFuture<GuildDTO?> =
        wrapper.createStandardNullableRequest("/guilds/$id", HttpMethod.Get, null)

    fun searchGuildByTag(tag: String): CompletableFuture<GuildDTO?> =
        wrapper.createStandardNullableRequest("/guilds?tag=$tag", HttpMethod.Get, null)

    fun updateGuild(id: Int, request: GuildUpdateRequest): CompletableFuture<Unit> =
        wrapper.createStandardRequest("/guilds/$id", HttpMethod.Put, request)

    fun deleteGuild(id: Int): CompletableFuture<Unit> =
        wrapper.createStandardRequest("/guilds/$id", HttpMethod.Delete, null)
}
