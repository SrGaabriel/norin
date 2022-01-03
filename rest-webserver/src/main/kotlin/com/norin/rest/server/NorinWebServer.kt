package com.norin.rest.server

import com.norin.rest.server.database.Database
import com.norin.rest.server.database.entity.AccountTable
import com.norin.rest.server.route.accountRoutes
import com.norin.rest.server.route.guildRoutes
import com.norin.rest.server.service.AccountDatabaseService
import com.norin.rest.server.service.GuildDatabaseService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@Suppress("unused")
@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.module() {
    install(CORS) {
        anyHost()
        allowCredentials = false

        header("Content-Type")
        header(HttpHeaders.Authorization)

        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
    }

    install(Locations)
    install(ContentNegotiation) {
        json()
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    Database
        .config(environment.config)
        .connect()
        .createTables(AccountTable)

    routing {
        accountRoutes(environment.config, AccountDatabaseService())
        guildRoutes(environment.config, GuildDatabaseService())
    }
}
