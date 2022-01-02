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
    var loggedAt by AccountTable.loggedAt
    var createdAt by AccountTable.createdAt
}

object AccountTable: UUIDTable("accounts") {
    val cash = integer("cash")
    val loggedAt = long("logged_at")
    val createdAt = long("created_at")
}

fun Account.mapToDto() = AccountDTO(id.value.toString(), cash, loggedAt, createdAt)
