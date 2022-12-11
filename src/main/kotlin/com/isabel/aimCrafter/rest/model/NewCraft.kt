package com.isabel.aimCrafter.rest.model

import java.time.Instant

data class NewCraft(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val howLong: Instant? = null,
    val difficultyLevel: Int? = null,
//    val token: String, // I think I need this, because a user can only post if they have an account
    val image: String
)

data class CraftResponseNew(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val howLong: Instant? = null,
    val difficultyLevel: Int? = null,
    val image: String,
)

data class CraftResponseShow(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val howLong: Instant? = null,
    val difficultyLevel: Int? = null,
    val image: String,
    val username: String
)

data class SimplifiedCraftResponse(
    val title: String,
    val image: String,
    val createdAt: Instant
)