package com.norin.rest.server.service

import com.norin.rest.common.GuildMemberPosition
import com.norin.rest.common.requests.MemberJoinGuildRequest
import com.norin.rest.common.requests.MemberUpdateRequest
import com.norin.rest.common.util.jvm.prettyUniqueId
import com.norin.rest.server.database.entity.Guild
import com.norin.rest.server.database.entity.GuildMember
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

class GuildMembersDatabaseService {
    suspend fun join(request: MemberJoinGuildRequest) = newSuspendedTransaction {
        if (find(request.userId.prettyUniqueId) != null)
            error("The specified user is already associated with a guild.")
        val guild = Guild.findById(request.guildId) ?: error("The specified guild ID is invalid.")
        GuildMember.new(request.userId.prettyUniqueId) {
            this.guild = guild
            this.position = GuildMemberPosition.LEADER.id
        }
    }

    suspend fun find(uniqueId: UUID) = newSuspendedTransaction {
        GuildMember.findById(uniqueId)
    }

    suspend fun findAll(guildId: Int) = newSuspendedTransaction {
        Guild.findById(guildId)?.members?.toList()
    }

    suspend fun update(uniqueId: UUID, request: MemberUpdateRequest) = newSuspendedTransaction {
        find(uniqueId)?.run {
            position = request.position.id
        }
    }

    suspend fun leave(uniqueId: UUID) = newSuspendedTransaction {
        val member = find(uniqueId)
            ?: error("The specified user is not associated with a guild.")
        member.delete()
    }
}
