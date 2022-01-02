package com.norin.rest.routes

import com.norin.rest.NorinRestWrapper
import com.norin.rest.common.entity.AccountDTO
import com.norin.rest.common.requests.AccountCreateRequest
import com.norin.rest.common.requests.AccountUpdateRequest
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

class AccountRoute(wrapper: NorinRestWrapper): RestRoute(wrapper) {
    fun createAccount(request: AccountCreateRequest): CompletableFuture<AccountDTO> =
        createStandardRequest("/accounts", HttpMethod.Post, request)

    fun searchAccount(uniqueId: String): CompletableFuture<AccountDTO?> =
        createStandardNullableRequest("/accounts/$uniqueId", HttpMethod.Get, null)

    fun updateAccount(uniqueId: String, request: AccountUpdateRequest): CompletableFuture<Unit> =
        createStandardRequest("/accounts/$uniqueId", HttpMethod.Patch, request)

    fun deleteAccount(uniqueId: String): CompletableFuture<Unit> =
        createStandardRequest("/accounts/$uniqueId", HttpMethod.Delete, null)
}
