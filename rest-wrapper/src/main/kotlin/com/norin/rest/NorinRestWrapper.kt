package com.norin.rest

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture
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

inline fun <T, reified L> NorinRestWrapper.createStandardRequest(endpoint: String, method: HttpMethod, body: T): CompletableFuture<L> = future {
    httpClient.request(apiUrl + endpoint) {
        this.method = method
        this.body = body ?: EmptyContent
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header(HttpHeaders.Authorization, authorization)
    }
}

inline fun <T, reified L> NorinRestWrapper.createStandardNullableRequest(endpoint: String, method: HttpMethod, body: T): CompletableFuture<L> =
    runCatching { createStandardRequest<T, L>(endpoint, method, body) }.getOrNull() ?: CompletableFuture.completedFuture(null)
