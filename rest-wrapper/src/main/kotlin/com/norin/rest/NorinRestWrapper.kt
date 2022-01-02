package com.norin.rest

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class NorinRestWrapper(val apiUrl: String, val authorization: String): CoroutineScope {
    val httpClient = HttpClient(CIO.create()) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
            acceptContentTypes = acceptContentTypes + ContentType.Application.Json
        }
    }
    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined
}
