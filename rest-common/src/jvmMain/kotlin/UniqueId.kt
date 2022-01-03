package com.norin.rest.common.util.jvm

import java.util.*

private val memo = mutableMapOf<String, UUID>()

fun getPrettyUUID(uniqueId: String) =
    memo[uniqueId] ?: UUID.fromString(uniqueId).also { memo[uniqueId] = it }

val String.prettyUniqueId get() = getPrettyUUID(this)
