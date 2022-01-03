@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.norin.rest.server.route

import com.norin.rest.server.database.entity.mapToDto
import com.norin.rest.server.service.AccountDatabaseService
import com.norin.rest.server.util.authorized
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.locations.patch
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Route.accountRoutes(config: ApplicationConfig, userService: AccountDatabaseService) = authorized(config) {
    post<Accounts> {
        call.respond(HttpStatusCode.Created, userService.create(call.receive()).mapToDto())
    }
    get<Accounts.Id> { (id) ->
        when (val user = userService.find(UUID.fromString(id))) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(user.mapToDto())
        }
    }
    patch<Accounts.Id> { (id) ->
        if (userService.update(UUID.fromString(id), call.receive()))
            call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
    delete<Accounts.Id> { (id) ->
        userService.delete(UUID.fromString(id))
        call.respond(HttpStatusCode.NoContent)
    }
}

@Location("/api/accounts")
class Accounts {

    @Location("/{id}")
    data class Id(val id: String, val accounts: Accounts)

}
