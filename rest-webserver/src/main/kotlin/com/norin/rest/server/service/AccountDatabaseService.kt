package com.norin.rest.server.service

import com.norin.rest.common.requests.AccountCreateRequest
import com.norin.rest.common.requests.AccountUpdateRequest
import com.norin.rest.server.database.entity.Account
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import prettyUniqueId
import java.util.*

class AccountDatabaseService {
    suspend fun create(create: AccountCreateRequest): Account = newSuspendedTransaction {
        find(create.prettyUniqueId) ?: Account.new(create.prettyUniqueId) {
            cash = 0
            loggedAt = System.currentTimeMillis()
            createdAt = System.currentTimeMillis()
        }
    }

    suspend fun update(id: UUID, update: AccountUpdateRequest): Boolean = newSuspendedTransaction {
        find(id)?.apply {
            cash = update.cash ?: cash
            loggedAt = update.loginDate ?: loggedAt
        } != null
    }

    suspend fun find(id: UUID): Account? = newSuspendedTransaction {
        Account.findById(id)
    }

    suspend fun delete(id: UUID) = newSuspendedTransaction {
        find(id)?.delete()
    }
}
