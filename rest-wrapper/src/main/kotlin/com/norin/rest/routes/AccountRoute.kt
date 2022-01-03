package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import com.norin.rest.common.entity.AccountDTO
import com.norin.rest.common.requests.AccountCreateRequest
import com.norin.rest.common.requests.AccountUpdateRequest
import com.norin.rest.createStandardNullableRequest
import com.norin.rest.createStandardRequest
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

class AccountRoute(val wrapper: NorinRestWrapper) {
    fun createAccount(request: AccountCreateRequest): CompletableFuture<AccountDTO> =
        wrapper.createStandardRequest("/accounts", HttpMethod.Post, request)

    fun searchAccount(uniqueId: String): CompletableFuture<AccountDTO?> =
        wrapper.createStandardNullableRequest("/accounts/$uniqueId", HttpMethod.Get, null)

    fun updateAccount(uniqueId: String, request: AccountUpdateRequest): CompletableFuture<Unit> =
        wrapper.createStandardRequest("/accounts/$uniqueId", HttpMethod.Put, request)

    fun deleteAccount(uniqueId: String): CompletableFuture<Unit> =
        wrapper.createStandardRequest("/accounts/$uniqueId", HttpMethod.Delete, null)
}
