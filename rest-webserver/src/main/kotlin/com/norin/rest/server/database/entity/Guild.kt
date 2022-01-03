package com.norin.rest.server.database.entity

import com.norin.rest.common.GuildMemberPosition
import com.norin.rest.common.entity.GuildDTO
import com.norin.rest.common.entity.GuildMemberDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Guild(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Guild>(GuildTable)

    var name by GuildTable.name
    var tag by GuildTable.tag
    var owner by GuildTable.owner
    var friendlyFire by GuildTable.friendlyFire
    val members by GuildMember referrersOn GuildMemberTable.guild
}

class GuildMember(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<GuildMember>(GuildMemberTable)

    var guild by Guild referencedOn GuildMemberTable.guild
    var position by GuildMemberTable.position
}

object GuildTable: IntIdTable("guilds") {
    val name = varchar("name", 12)
    val tag = varchar("tag", 3)
    val owner = uuid("owner_id")
    val friendlyFire = bool("friendly_fire").default(false)
}

object GuildMemberTable: UUIDTable("members") {
    val guild = reference("guild", GuildTable)
    val position = integer("position")
}

fun Guild.mapToDto() = GuildDTO(id.value, name, tag, owner.toString(), friendlyFire)

fun GuildMember.mapToDto() =
    GuildMemberDTO(id.value.toString(), guild.id.value, GuildMemberPosition.fromId(position))
