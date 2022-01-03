package com.norin.rest.server.service

import com.norin.rest.common.requests.GuildCreateRequest
import com.norin.rest.common.requests.GuildUpdateRequest
import com.norin.rest.common.util.jvm.prettyUniqueId
import com.norin.rest.server.database.entity.Guild
import com.norin.rest.server.database.entity.GuildTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class GuildDatabaseService {
    suspend fun create(request: GuildCreateRequest) = newSuspendedTransaction {
        if (findByTag(request.tag) != null)
            return@newSuspendedTransaction null
        Guild.new {
            name = request.name
            tag = request.tag
            owner = request.ownerId.prettyUniqueId
        }
    }

    suspend fun find(id: Int) = newSuspendedTransaction {
        Guild.findById(id)
    }

    suspend fun findByTag(tag: String) = newSuspendedTransaction {
        Guild.find { GuildTable.tag eq tag }.firstOrNull()
    }

    suspend fun update(id: Int, request: GuildUpdateRequest): Boolean = newSuspendedTransaction {
        val guild = find(id) ?: return@newSuspendedTransaction false
        guild.apply {
            name = request.name
            tag = request.tag
            owner = request.ownerId.prettyUniqueId
        }; true
    }

    suspend fun delete(id: Int): Boolean = newSuspendedTransaction {
        find(id)?.delete() != null
    }
}
