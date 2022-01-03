@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.norin.rest.server.route

import com.norin.rest.server.database.entity.mapToDto
import com.norin.rest.server.service.GuildDatabaseService
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

@Location("/api/guilds")
class Guilds {

    @Location("/{id}")
    data class Id(val id: Int, val guilds: Guilds)

    @Location("/")
    data class Tag(val tag: String, val guilds: Guilds)
}
