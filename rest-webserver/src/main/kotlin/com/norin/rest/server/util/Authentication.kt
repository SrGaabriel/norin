package com.norin.rest.server.util

import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.authorized(config: ApplicationConfig, route: Route.() -> Unit): Route {
    intercept(ApplicationCallPipeline.Features) {
        validate(config, call.request.authorization().orEmpty()) {
            finish()
        }
    }
    return apply(route)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.validate(config: ApplicationConfig, value: String, callback: (String) -> Unit) = if (config.property("keys").getList().contains(value).not())
    call.respond(HttpStatusCode.Unauthorized).also { callback(value) } else Unit
