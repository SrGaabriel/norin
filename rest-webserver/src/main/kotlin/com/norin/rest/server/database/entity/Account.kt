package com.norin.rest.server.database.entity

import com.norin.rest.common.entity.AccountDTO
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Account(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<Account>(AccountTable)

    var cash by AccountTable.cash
    var guild by AccountTable.guild
    var loggedAt by AccountTable.loggedAt
    var createdAt by AccountTable.createdAt
}

object AccountTable: UUIDTable("accounts") {
    val cash = integer("cash").default(0)
    /* This could be later replaced by the user's own membership, to make things simpler
    (although it would only benefit the server) */
    val guild = integer("guild_id").nullable().default(null)
    val loggedAt = long("logged_at")
    val createdAt = long("created_at")
}

fun Account.mapToDto() = AccountDTO(id.value.toString(), cash, guild, loggedAt, createdAt)
