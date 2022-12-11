package com.isabel.aimCrafter.rest.model

data class LikeInfo(
    val userId: Int, // user that is giving a like
    val craftId: Int // craft that is receiving the like
)

data class CraftInfo(
    val craftId: Int,
    val likesCount: Int
)
