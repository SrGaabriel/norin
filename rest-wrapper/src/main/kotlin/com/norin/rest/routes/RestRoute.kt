package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import kotlinx.coroutines.future.future
import kotlin.runCatching
import java.util.concurrent.CompletableFuture

abstract class RestRoute(val wrapper: NorinRestWrapper) {
    inline fun <T, reified L> createStandardRequest(endpoint: String, method: HttpMethod, body: T): CompletableFuture<L> = wrapper.future {
        wrapper.httpClient.request(wrapper.apiUrl + endpoint) {
            this.method = method
            this.body = body ?: EmptyContent
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Authorization, wrapper.authorization)
        }
    }

    inline fun <T, reified L> createStandardNullableRequest(endpoint: String, method: HttpMethod, body: T): CompletableFuture<L> =
        runCatching { createStandardRequest<T, L>(endpoint, method, body) }.getOrNull() ?: CompletableFuture.completedFuture(null)
}
