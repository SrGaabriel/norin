package com.norin.rest.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmStatic

@Serializable(GuildMemberPositionSerializer::class)
enum class GuildMemberPosition(val id: Int) {
    LEADER(3), CAPTAIN(2), MEMBER(1), ROOKIE(0);

    companion object {
        @JvmStatic
        fun fromId(id: Int): GuildMemberPosition = values().first {
            it.id == id
        }
    }
}

object GuildMemberPositionSerializer: KSerializer<GuildMemberPosition> {
    override fun deserialize(decoder: Decoder): GuildMemberPosition =
        GuildMemberPosition.values().first { it.id == decoder.decodeInt() }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("GuildMemberPosition", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: GuildMemberPosition) =
        encoder.encodeInt(value.id)
}
