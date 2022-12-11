package com.isabel.aimCrafter.rest.model

import java.time.Instant
import kotlin.time.Duration

data class NewCraft(
    val title: String,
    val userId: Int,
    val tools: List<String>,
    val description: String? = null,
    val howLong: Duration? = null,
    val difficultyLevel: Int? = null,
    val token: String, // I think I need this, because a user can only post if they have an account
    val image: String
)

data class CraftResponse(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val howLong: Duration? = null,
    val difficultyLevel: Int? = null,
    val username: String,
    val image: String
)

data class SimplifiedCraftResponse(
    val title: String,
    val image: String,
    val createdAt: Instant
)