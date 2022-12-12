package com.isabel.aimCrafter.rest.model

data class LikeInfo(
    val craftId: Long
)

data class CraftInfo(
    val craftId: Long,
    val likesCount: Long
)
