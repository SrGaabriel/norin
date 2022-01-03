@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.norin.rest.server.route

import com.norin.rest.common.util.jvm.prettyUniqueId
import com.norin.rest.server.database.entity.GuildMember
import com.norin.rest.server.database.entity.mapToDto
import com.norin.rest.server.service.GuildDatabaseService
import com.norin.rest.server.service.GuildMembersDatabaseService
import com.norin.rest.server.util.authorized
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.guildRoutes(config: ApplicationConfig, guildService: GuildDatabaseService) = authorized(config) {
    post<Guilds> {
        when (val guild = guildService.create(call.receive())) {
            null -> call.respond(HttpStatusCode.Conflict)
            else -> call.respond(HttpStatusCode.Created, guild.mapToDto())
        }
    }
    get<Guilds.Id> { (id) ->
        when (val guild = guildService.find(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(guild.mapToDto())
        }
    }
    get<Guilds.Tag> { (tag) ->
        when (val guild = guildService.findByTag(tag)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(guild.mapToDto())
        }
    }
    put<Guilds.Id> { (id) ->
        if (guildService.update(id, call.receive()))
            call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
    delete<Guilds.Id> { (id) ->
        if (guildService.delete(id))
            call.respond(HttpStatusCode.NoContent)
        else call.respond(HttpStatusCode.NotFound)
    }
}

fun Route.guildMemberRoutes(config: ApplicationConfig, memberService: GuildMembersDatabaseService) = authorized(config) {
    post<Guilds.Members> {
        runCatching { memberService.join(call.receive()) }
            .onSuccess { call.respond(it.mapToDto()) }
            .onFailure { call.respond(HttpStatusCode.NotAcceptable) }
    }
    get<Guilds.Members> { (guildId) ->
        when (val members = memberService.findAll(guildId.id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(members.map(GuildMember::mapToDto))
        }
    }
    get<Guilds.Members.Id> { (id) ->
        when (val member = memberService.find(id.prettyUniqueId)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(member.mapToDto())
        }
    }
    put<Guilds.Members.Id> { (id) ->
        memberService.update(id.prettyUniqueId, call.receive())
        call.respond(HttpStatusCode.OK)
    }
    delete<Guilds.Members.Id> { (id) ->
        memberService.leave(id.prettyUniqueId)
        call.respond(HttpStatusCode.NoContent)
    }
}

@Location("/api/guilds")
class Guilds {

    @Location("/{id}")
    data class Id(val id: Int, val guilds: Guilds)

    @Location("/members")
    data class Members(val id: Guilds.Id) {
        @Location("/{id}")
        data class Id(val id: String, val members: Members)
    }

    @Location("/")
    data class Tag(val tag: String, val guilds: Guilds)
}
