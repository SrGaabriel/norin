package com.norin.rest.server.service

import com.norin.rest.common.requests.AccountCreateRequest
import com.norin.rest.common.requests.AccountUpdateRequest
import com.norin.rest.common.util.jvm.prettyUniqueId
import com.norin.rest.server.database.entity.Account
import com.norin.rest.server.database.entity.Guild
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

class AccountDatabaseService {
    suspend fun create(create: AccountCreateRequest): Account = newSuspendedTransaction {
        find(create.uniqueId.prettyUniqueId) ?: Account.new(create.uniqueId.prettyUniqueId) {
            loggedAt = System.currentTimeMillis()
            createdAt = System.currentTimeMillis()
        }
    }

    suspend fun find(id: UUID): Account? = newSuspendedTransaction {
        Account.findById(id)
    }

    suspend fun update(id: UUID, update: AccountUpdateRequest): Boolean = newSuspendedTransaction {
        val account = find(id) ?: return@newSuspendedTransaction false
        account.apply {
            cash = update.cash
            guild = update.guildId
            loggedAt = update.loginDate
        }; true
    }

    suspend fun delete(id: UUID) = newSuspendedTransaction {
        find(id)?.delete()
    }
}
