@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.norin.rest.server.route

import com.norin.rest.common.util.jvm.prettyUniqueId
import com.norin.rest.server.database.entity.mapToDto
import com.norin.rest.server.service.AccountDatabaseService
import com.norin.rest.server.util.authorized
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.locations.put
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.accountRoutes(config: ApplicationConfig, userService: AccountDatabaseService) = authorized(config) {
    post<Accounts> {
        call.respond(HttpStatusCode.Created, userService.create(call.receive()).mapToDto())
    }
    get<Accounts.Id> { (id) ->
        when (val user = userService.find(id.prettyUniqueId)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(user.mapToDto())
        }
    }
    put<Accounts.Id> { (id) ->
        if (userService.update(id.prettyUniqueId, call.receive()))
            call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
    delete<Accounts.Id> { (id) ->
        userService.delete(id.prettyUniqueId)
        call.respond(HttpStatusCode.NoContent)
    }
}

@Location("/api/accounts")
class Accounts {

    @Location("/{id}")
    data class Id(val id: String, val accounts: Accounts)

}
